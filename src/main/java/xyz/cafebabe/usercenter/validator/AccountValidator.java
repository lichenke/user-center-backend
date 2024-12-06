package xyz.cafebabe.usercenter.validator;

import xyz.cafebabe.usercenter.validator.annotation.ValidAccount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class AccountValidator implements ConstraintValidator<ValidAccount, String> {

    private static final String ACCOUNT_PATTERN = "^[a-zA-Z][a-zA-Z0-9_]{5,31}$";

    @Override
    public boolean isValid(String account, ConstraintValidatorContext context) {
        if (account == null || account.trim().isEmpty()) {
            return false;
        }
        return Pattern.compile(ACCOUNT_PATTERN).matcher(account).matches();
    }
}
