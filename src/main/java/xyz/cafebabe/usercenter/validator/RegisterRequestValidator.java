package xyz.cafebabe.usercenter.validator;

import xyz.cafebabe.usercenter.exception.ParamValidateException;
import xyz.cafebabe.usercenter.model.domain.request.RegisterRequest;
import xyz.cafebabe.usercenter.service.PasswordService;
import xyz.cafebabe.usercenter.validator.annotation.ValidRegisterRequest;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static xyz.cafebabe.usercenter.common.ResponseCode.DIFFERENT_PASSWORD_ERROR;
import static xyz.cafebabe.usercenter.common.ResponseCode.PASSWORD_VALIDATE_ERROR;

/**
 * 对注册请求进行业务层的校验
 */
public class RegisterRequestValidator implements ConstraintValidator<ValidRegisterRequest, RegisterRequest> {

    @Resource
    private PasswordService passwordService;

    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext context) {
        String password = request.getPassword();
        String checkPassword = request.getCheckPassword();
        // 密码和校验密码是否相同
        if (!password.equals(checkPassword)) {
            throw new ParamValidateException(DIFFERENT_PASSWORD_ERROR, "‘password’和‘checkPassword’不相同");
        }
        // 密码是否符合要求
        if (!passwordService.isValid(password)) {
            throw new ParamValidateException(PASSWORD_VALIDATE_ERROR, "‘password’不符合要求");
        }
        return true;
    }
}
