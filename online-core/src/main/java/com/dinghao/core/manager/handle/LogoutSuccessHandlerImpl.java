package com.dinghao.core.manager.handle;


import com.alibaba.fastjson.JSON;
import com.dinghao.common.constant.Constants;
import com.dinghao.common.constant.HttpStatus;
import com.dinghao.common.core.domain.model.LoginUser;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.ServletUtils;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.core.manager.AsyncManager;
import com.dinghao.core.manager.factory.AsyncFactory;
import com.dinghao.core.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(ResultBody.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
