package xyz.cafebabe.usercenter.validator;

import xyz.cafebabe.usercenter.model.domain.request.RegisterRequest;
import xyz.cafebabe.usercenter.validator.annotation.ValidRegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 对注册请求进行业务层的校验
 */
public class RegisterRequestValidator implements ConstraintValidator<ValidRegisterRequest, RegisterRequest> {


    @Override
    public boolean isValid(RegisterRequest value, ConstraintValidatorContext context) {
        return false;
    }
}
