package xyz.cafebabe.usercenter.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.cafebabe.usercenter.common.BaseResponse;

import static xyz.cafebabe.usercenter.common.ResponseCode.PARAM_VALIDATE_ERROR;

/**
 * 控制器全局异常处理切面增强类
 */

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 只要触发了该异常，任意一个验证点都可以抛出，所以可以使用get(0)
        String description = e.getAllErrors().get(0).getDefaultMessage();
        return BaseResponse.fail(PARAM_VALIDATE_ERROR, description);
    }
}
