package xyz.cafebabe.usercenter.common;

public enum ResponseCode implements StatusCode {
    SUCCESS(10000, "请求成功"),

    PARAM_VALIDATE_ERROR(40001, "请求参数校验失败");

    private final int code;

    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
