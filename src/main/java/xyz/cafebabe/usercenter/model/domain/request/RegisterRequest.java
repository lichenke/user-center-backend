package xyz.cafebabe.usercenter.model.domain.request;

import lombok.Data;
import xyz.cafebabe.usercenter.validator.annotation.ValidAccount;
import xyz.cafebabe.usercenter.validator.annotation.ValidPassword;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 注册请求对象
 */

@Data
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ValidAccount
    private String account;

    @ValidPassword
    private String password;

    @NotBlank(message = "‘checkPassword’不能为空")
    private String checkPassword;
}
