package xyz.cafebabe.usercenter.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.cafebabe.usercenter.common.BaseResponse;
import xyz.cafebabe.usercenter.common.StatusCode;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static xyz.cafebabe.usercenter.common.ResponseCode.BIZ_ERROR;
import static xyz.cafebabe.usercenter.common.ResponseCode.PARAM_INVALID_ERROR;

/**
 * 控制器全局异常处理切面增强类
 */

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 接管全局因接口参数校验失败而抛出的异常
     *
     * @param e MethodArgumentNotValidException
     * @return 通用对象返回
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 只要触发了该异常，任意一个验证点都可以抛出，所以可以使用get(0)
        String message = e.getAllErrors().get(0).getDefaultMessage();
        log.warn(message, e);
        return BaseResponse.fail(PARAM_INVALID_ERROR, message);
    }

    /**
     * 接管全局因接口参数校验失败而抛出的异常
     *
     * @param e ConstraintViolationException
     * @return 通用对象返回
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<?> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        ConstraintViolation<?> o = (ConstraintViolation) constraintViolations.toArray()[0];
        String message = o.getMessage();
        log.error(message, e);
        return BaseResponse.fail(PARAM_INVALID_ERROR, message);
    }

    /**
     * 接管全局业务异常
     *
     * @param e BusinessException
     * @return 通用对象返回
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> BusinessExceptionHandler(BusinessException e) {
        String message = e.getMessage();
        log.error(message, e);
        return BaseResponse.fail(BIZ_ERROR, message);
    }

    /**
     * 接管全局因参数未通过业务层校验而抛出的异常
     *
     * @param e ParamValidateException
     * @return 通用对象返回
     */
    @ExceptionHandler(ParamInvalidException.class)
    public BaseResponse<?> ParamValidateExceptionHandler(ParamInvalidException e) {
        String message = e.getMessage();
        StatusCode code = e.getCode();
        log.warn(message, e);
        return BaseResponse.fail(code, message);
    }
}
