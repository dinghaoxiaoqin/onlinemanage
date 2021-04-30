package com.dinghao.common.exception;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 自定义异常
 * 
 * @author ruoyi
 */
public class CustomException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    public CustomException(String message)
    {
        this.message = message;
    }

    public CustomException(String message, String code)
    {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public String getCode()
    {
        return code;
    }
}
