package xyz.cafebabe.usercenter.exception;

import lombok.Getter;

/**
 * 全局业务异常类
 */

@Getter
public class BusinessException extends RuntimeException {

    /**
     * 业务异常的详细说明
     */
    private String description;

    public BusinessException(String message, String description) {
        super(message);
        this.description = description;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable t, Object... params) {
        super(String.format(message, params), t);
    }
}
