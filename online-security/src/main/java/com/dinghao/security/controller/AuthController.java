package com.dinghao.security.controller;



import com.dinghao.common.core.domain.entity.SysUser;
import com.dinghao.common.core.domain.model.LoginBody;
import com.dinghao.common.core.domain.model.LoginUser;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.ServletUtils;
import com.dinghao.core.web.service.TokenService;
import com.dinghao.system.domain.vo.RouterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dinghao.core.web.service.SysLoginService;

import java.util.List;
import java.util.Map;


/**
 * 后台用户登录入口
 */

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin
public class AuthController {


    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private TokenService tokenService;


    /**
     * 用户登录
     */
    @PostMapping(value = "/login")
    public ResultBody login(@RequestBody LoginBody loginBody) {
        String token = sysLoginService.login(loginBody);
        return ResultBody.success(token);
    }

    /**
     * 根据用户id获取用户信息
     */
    @GetMapping(value = "/getInfo")
    public ResultBody getInfo(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        Map<String,Object> result = sysLoginService.getInfo(user);
        return ResultBody.success(result);
    }

    /**
     * 获取路由信息
     */
    @GetMapping(value = "/getRouters")
    public ResultBody getRouters(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<RouterVo> list = sysLoginService.buildMenus(user);
        return ResultBody.success(list);
    }

}
