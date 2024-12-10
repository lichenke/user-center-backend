package xyz.cafebabe.usercenter.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.cafebabe.usercenter.common.BaseResponse;
import xyz.cafebabe.usercenter.common.StatusCode;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static xyz.cafebabe.usercenter.common.ResponseCode.*;

/**
 * 控制器全局异常处理切面增强类
 */

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 只要触发了该异常，任意一个验证点都可以抛出，所以可以使用get(0)
        String message = e.getAllErrors().get(0).getDefaultMessage();
        log.warn(message, e);
        return BaseResponse.fail(PARAM_INVALID_ERROR, message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<?> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        ConstraintViolation<?> o = (ConstraintViolation) constraintViolations.toArray()[0];
        String message = o.getMessage();
        log.error(message, e);
        return BaseResponse.fail(PARAM_INVALID_ERROR, message);
    }

    @ExceptionHandler(NotLoginException.class)
    public BaseResponse<?> NotLoginExceptionHandler(NotLoginException e) {
        String message = e.getMessage();
        log.error(message, e);
        return BaseResponse.fail(NOT_LOGIN, message);
    }

    @ExceptionHandler(NotPermissionException.class)
    public BaseResponse<?> NotPermissionExceptionHandler(NotPermissionException e) {
        String message = e.getMessage();
        log.error(message, e);
        return BaseResponse.fail(NO_PERMISSION, message);
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> BusinessExceptionHandler(BusinessException e) {
        String message = e.getMessage();
        log.error(message, e);
        return BaseResponse.fail(BIZ_ERROR, message);
    }

    @ExceptionHandler(ParamInvalidException.class)
    public BaseResponse<?> ParamValidateExceptionHandler(ParamInvalidException e) {
        String message = e.getMessage();
        StatusCode code = e.getCode();
        log.warn(message, e);
        return BaseResponse.fail(code, message);
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> RuntimeExceptionHandler(RuntimeException e) {
        String message = e.getMessage();
        log.error(message, e);
        return BaseResponse.fail(BIZ_ERROR, message);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> ExceptionHandler(Exception e) {
        String message = e.getMessage();
        log.error(message, e);
        return BaseResponse.fail(BIZ_ERROR, message);
    }
}
