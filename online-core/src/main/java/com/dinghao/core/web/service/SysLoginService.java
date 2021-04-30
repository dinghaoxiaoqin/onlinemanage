package com.dinghao.core.web.service;

import cn.hutool.core.util.StrUtil;
import com.dinghao.common.constant.Constants;
import com.dinghao.common.core.domain.entity.SysMenu;
import com.dinghao.common.core.domain.entity.SysUser;
import com.dinghao.common.core.domain.model.LoginBody;
import com.dinghao.common.core.domain.model.LoginUser;
import com.dinghao.common.exception.CustomException;
import com.dinghao.common.exception.user.CaptchaException;
import com.dinghao.common.exception.user.CaptchaExpireException;
import com.dinghao.common.exception.user.UserPasswordNotMatchException;
import com.dinghao.common.utils.MessageUtils;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.common.utils.ToolUtil;
import com.dinghao.core.manager.AsyncManager;
import com.dinghao.core.manager.factory.AsyncFactory;

import com.dinghao.system.domain.vo.RouterVo;
import com.dinghao.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统用户登录
 *
 * @author dinghao
 */
@Component
public class SysLoginService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysMenuService sysMenuService;


    /**
     * 用户登录
     *
     * @param loginBody
     * @return
     */
    public String login(LoginBody loginBody) {
        //验证码的key
        String verifyKey = Constants.CAPTCHA_CODE_KEY + loginBody.getUuid();
        String captcha = redisTemplate.opsForValue().get(verifyKey);
        //删除之前保存的验证码
        redisTemplate.delete(verifyKey);
        //验证码过期
        if (StrUtil.isBlank(captcha)) {
            //通过异步的方式记录用户登录信息
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginBody.getUsername(), Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        //验证码输入错误
        if (!ToolUtil.equals(loginBody.getCode(), captcha)) {
            //通过异步的方式记录用户登录信息
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginBody.getUsername(), Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        //登录成功，返回token
        return createToken(loginBody.getUsername(), loginBody.getPassword());

    }

    private String createToken(String username, String password) {
        //用户验证
        Authentication au = null;
        //将用户登录的账号和密码交给security认证管理器
        try {
            au = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                //用户账户或密码不匹配
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                //未知异常
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        //通过异步方式记录登录成功的信息
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) au.getPrincipal();
        //生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 根据用户id获取用户基本信息
     *
     * @param user
     * @return
     */
    public Map<String, Object> getInfo(SysUser user) {
        Map<String, Object> result = new HashMap<>();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        result.put("user", user);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return result;
    }

    /**
     * 获取路由信息
     *
     * @param user
     * @return
     */
    public List<RouterVo> buildMenus(SysUser user) {
        List<SysMenu> menus = sysMenuService.selectMenuTreeByUserId(user.getUserId());
        List<RouterVo> list = sysMenuService.buildMenus(menus);
        return list;
    }


}
