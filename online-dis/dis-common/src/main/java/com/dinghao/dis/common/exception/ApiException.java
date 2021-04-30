package com.dinghao.dis.common.exception;

/**
 * @author 天天向上 （john.yi@qq.com）
 * @date 2020/9/1.
 */
public class ApiException extends RuntimeException {
    private Integer code;

    private String message;

    public ApiException(String message) {
        this.message = message;
    }

    public ApiException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public ApiException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
