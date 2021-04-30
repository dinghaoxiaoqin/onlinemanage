package com.dinghao.dis.common.exception;

/**
 * 在业务层手动抛出业务意义不明确的异常时使用
 * 注意：具体的业务异常不要使用本类，应该自定义一个具体的异常类继承此类
 * 例如：CancelOrderException，
 * @author 天天向上 （john.yi@qq.com）
 * @date 2020/9/1.
 */
public class BizException extends Exception {
    private Integer code;

    private String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public BizException(String message, Throwable e) {
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
