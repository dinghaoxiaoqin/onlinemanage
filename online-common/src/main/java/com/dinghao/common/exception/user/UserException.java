package com.dinghao.common.exception.user;


import com.dinghao.common.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author dinghao
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
