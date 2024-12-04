package xyz.cafebabe.usercenter.common;

import lombok.Data;

import java.io.Serializable;


@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 业务状态码
    private int code;

    // 接口调用结果信息
    private String msg;

    // 接口调用结果详情：对msg的扩展（主要用于说明异常情况）
    private String description;

    // 接口返回数据
    private T data;

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> BaseResponse<T> success(T data) {
        ErrorCode success = ErrorCode.SUCCESS;
        return new BaseResponse<>(success.getCode(), success.getMessage(), data);
    }

//    public static <T> BaseResponse<T> nullParams(String paramName) {
//        ErrorCode paramNullError = ErrorCode.PARAM_NULL_ERROR;
//        String message = paramNullError.getMessage();
//        return new BaseResponse<>(paramNullError.getCode(), String.format(message, paramName));
//    }
//
//    public static <T> BaseResponse<T> blankParams(String paramName) {
//        ErrorCode paramBlankError = ErrorCode.PARAM_BLANK_ERROR;
//        String message = paramBlankError.getMessage();
//        return new BaseResponse<>(paramBlankError.getCode(), String.format(message, paramName));
//    }

}
