package xyz.cafebabe.usercenter.validator.annotation;


import xyz.cafebabe.usercenter.validator.AccountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountValidator.class)
public @interface ValidAccount {

    String message() default "'account’不符合要求";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
