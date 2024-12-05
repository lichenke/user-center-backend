package xyz.cafebabe.usercenter.model.domain.request;

import lombok.Data;
import xyz.cafebabe.usercenter.validator.annotation.ValidRegisterRequest;
import xyz.cafebabe.usercenter.validator.group.ControllerValidation;
import xyz.cafebabe.usercenter.validator.group.ServiceValidation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 注册请求对象
 */

@Data
@ValidRegisterRequest(groups = ServiceValidation.class)
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(groups = {ServiceValidation.class, ControllerValidation.class}, message = "‘account’不能为空")
    @Pattern(groups = {ServiceValidation.class},
            regexp = "^[a-zA-Z][a-zA-Z0-9_]{5,31}$", message = "‘account’不符合要求")
    private String account;

    @NotBlank(groups = {ServiceValidation.class, ControllerValidation.class}, message = "‘password’不能为空")
    private String password;

    @NotBlank(groups = {ServiceValidation.class, ControllerValidation.class}, message = "‘checkPassword’不能为空")
    private String checkPassword;
}
