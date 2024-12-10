package xyz.cafebabe.usercenter.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 业务状态码
    private final int code;

    // 接口调用结果信息
    private final String msg;

    // 接口调用结果详情：对msg的扩展（主要用于说明异常情况）
    private String description;

    // 接口返回数据
    private T data;

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(int code, String msg, String description) {
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public static <T> BaseResponse<T> success(T data) {
        ResponseCode success = ResponseCode.SUCCESS;
        return new BaseResponse<>(success.getCode(), success.getMessage(), data);
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static <T> BaseResponse<T> fail(StatusCode errCode, String description) {
        int code = errCode.getCode();
        String message = errCode.getMessage();
        return new BaseResponse<>(code, message, description);
    }
}
