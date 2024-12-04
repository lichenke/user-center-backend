package xyz.cafebabe.usercenter.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS(10000, "请求成功"),

    PARAM_NULL_ERROR(40001, "请求参数为NULL"),

    PARAM_BLANK_ERROR(40002, "请求参数为空");

    private final int code;

    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
