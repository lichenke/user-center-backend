package xyz.cafebabe.usercenter.exception;

import lombok.Getter;
import xyz.cafebabe.usercenter.common.ErrorCode;

import java.util.Arrays;

/**
 * 请求的参数异常类
 */

@Getter
public class ParamException extends BusinessException {

    private static final String NULL_PARAM_DESCRIPTION_FORMATTER = "请求参数: {{%s}}为NULL";

    private static final String BLANK_PARAM_DESCRIPTION_FORMATTER = "请求参数: {{%s}}为空";

    private ParamException(String message, String description) {
        super(message, description);
    }

    public static ParamException nullParamException(String paramName) {
        ErrorCode paramNullError = ErrorCode.PARAM_NULL_ERROR;
        String message = paramNullError.getMessage();
        String description = String.format(NULL_PARAM_DESCRIPTION_FORMATTER, paramName);
        return new ParamException(message, description);
    }

    public static ParamException blankParamException(String paramName) {
        ErrorCode paramBlankError = ErrorCode.PARAM_BLANK_ERROR;
        String message = paramBlankError.getMessage();
        String description = String.format(BLANK_PARAM_DESCRIPTION_FORMATTER, paramName);
        return new ParamException(message, description);
    }

    public static ParamException hasBlankParamException(String... params) {
        ErrorCode paramBlankError = ErrorCode.PARAM_BLANK_ERROR;
        String message = paramBlankError.getMessage();
        String paramName = Arrays.toString(params);
        String description = String.format(BLANK_PARAM_DESCRIPTION_FORMATTER, paramName);
        return new ParamException(message, description);
    }

}
