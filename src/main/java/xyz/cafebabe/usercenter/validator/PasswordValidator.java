package xyz.cafebabe.usercenter.validator;

import xyz.cafebabe.usercenter.service.PasswordService;
import xyz.cafebabe.usercenter.validator.annotation.ValidPassword;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Resource
    private PasswordService passwordService;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return passwordService.isValid(password);
    }
}
