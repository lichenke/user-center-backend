package xyz.cafebabe.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;


@Data
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private String password;

    private String checkPassword;
}
