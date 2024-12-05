package xyz.cafebabe.usercenter.validator.annotation;

import xyz.cafebabe.usercenter.validator.RegisterRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegisterRequestValidator.class)
public @interface ValidRegisterRequest {

    String message() default "RegisterRequest未能通过校验";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
