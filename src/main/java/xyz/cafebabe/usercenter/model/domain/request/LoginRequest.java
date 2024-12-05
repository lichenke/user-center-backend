package xyz.cafebabe.usercenter.model.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 登录请求对象
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "‘账号’不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{5,31}$", message = "非法的‘账号’内容")
    private String account;

    @NotBlank(message = "‘密码’不能为空")
    private String password;
}
