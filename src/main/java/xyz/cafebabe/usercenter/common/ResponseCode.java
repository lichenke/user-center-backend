package xyz.cafebabe.usercenter.common;

public enum ResponseCode implements StatusCode {
    SUCCESS(10000, "请求成功"),

    PARAM_INVALID_ERROR(40001, "请求参数校验失败"),

    BIZ_ERROR(50001, "业务异常"),

    NOT_LOGIN(50002, "未登录"),

    NO_PERMISSION(50003, "无权限");

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
