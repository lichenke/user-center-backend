package xyz.cafebabe.usercenter.model.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "‘账号’不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{5,31}$", message = "‘账号’需以字母开头，包含数字和下划线，长度在6-32之间，不能包含其他特殊字符")
    private String account;

    @NotBlank(message = "‘密码’不能为空")
    private String password;

    @NotBlank(message = "‘确认密码’不能为空")
    private String checkPassword;
}
