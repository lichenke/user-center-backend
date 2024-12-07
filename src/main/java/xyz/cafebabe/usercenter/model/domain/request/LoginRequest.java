package xyz.cafebabe.usercenter.model.domain.request;

import lombok.Data;
import xyz.cafebabe.usercenter.validator.annotation.ValidAccount;
import xyz.cafebabe.usercenter.validator.annotation.ValidPassword;

import java.io.Serializable;

/**
 * 登录请求对象
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ValidAccount
    private String account;

    @ValidPassword
    private String password;
}
