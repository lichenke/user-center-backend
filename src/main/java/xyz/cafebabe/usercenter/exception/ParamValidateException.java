package xyz.cafebabe.usercenter.exception;

import lombok.Getter;
import xyz.cafebabe.usercenter.common.StatusCode;

@Getter
public class ParamValidateException extends RuntimeException {

    private final StatusCode code;

    public ParamValidateException(StatusCode code, String message) {
        super(message);
        this.code = code;
    }




}
