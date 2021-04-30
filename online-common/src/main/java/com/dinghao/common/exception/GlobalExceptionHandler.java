package com.dinghao.common.exception;


import com.dinghao.common.constant.HttpStatus;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {


    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    public ResultBody bizExceptionHandler(HttpServletRequest req, MyException e){
        log.error("发生业务异常！原因是：e->{}",e.getMsg());
        return ResultBody.error(e.getCode(),e.getMsg());
    }


    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:e->{}",e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理请求方法不支持的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResultBody exceptionHandler2(HttpServletRequest req, HttpRequestMethodNotSupportedException e){
        log.error("发生请求方法不支持异常！原因是:",e);
        return ResultBody.error(CommonEnum.REQUEST_METHOD_SUPPORT_ERROR);
    }


    /**
     * 处理请求方法不支持的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ParamIsNullException.class,MissingServletRequestParameterException.class})
    public ResultBody exceptionHandler3(HttpServletRequest req, Exception  e){
        log.error("参数为空！原因是:",e);
        return ResultBody.error(CommonEnum.SIGNATURE_NOT_MATCH.getResultCode(),e.getMessage());
    }




    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return ResultBody.error("609","未知异常，请联系管理员");
    }

    /**
     * 基础异常
     */
    @ExceptionHandler(value = BaseException.class)
    public ResultBody baseExceptionHandler(BaseException e)
    {
        log.error("基础异常！原因是:",e);
        return ResultBody.error("506",e.getMessage());
    }

    /**
     * 登录异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =LoginException.class)
    public ResultBody loginExceptionHandler(HttpServletRequest req, Exception e){
        log.error("登录出现异常:",e);
        return ResultBody.error(CommonEnum.LOGIN_ERROR);
    }

    /**
     * 路径异常
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResultBody noHandlerFoundExceptionHandler(HttpServletRequest req,Exception e){
        log.error(e.getMessage(),e);
        return ResultBody.error(HttpStatus.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }
    /**
     * 无权限，请联系管理员授权
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResultBody accessDeniedExceptionHandler(HttpServletRequest req,Exception e){
        log.error(e.getMessage(),e);
        return ResultBody.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }
    /**
     * 账户过期异常
     */
    @ExceptionHandler(value = AccountExpiredException.class)
    public ResultBody accountExpiredExceptionHandler(HttpServletRequest req,Exception e){
        log.error(e.getMessage(), e);
        return ResultBody.error(CommonEnum.TOKEN_EXPIRE);
    }

    /**
     * 账户不存在异常
     */
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResultBody usernameNotFoundExceptionHandler(HttpServletRequest req,Exception e){
        log.error(e.getMessage(), e);
        return ResultBody.error(CommonEnum.USER_INPUT_ERROR);
    }


}
