package xyz.cafebabe.usercenter.exception;

import lombok.Getter;
import xyz.cafebabe.usercenter.common.StatusCode;

@Getter
public class ParamInvalidException extends RuntimeException {

    private final StatusCode code;

    public ParamInvalidException(StatusCode code, String message) {
        super(message);
        this.code = code;
    }




}
