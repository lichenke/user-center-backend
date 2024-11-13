package xyz.cafebabe.usercenter.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.cafebabe.usercenter.service.PasswordService;

import java.security.SecureRandom;
import java.util.regex.Pattern;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
            BCRYPT_STRENGTH, new SecureRandom()
    );

    @Override
    public String encryptPassword(String rawPassword) {
        if (!isValidPassword(rawPassword)) {
            throw new IllegalArgumentException("密码不符合安全要求");
        }
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean isValidPassword(String rawPassword) {
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            return false;
        }
        return Pattern.compile(PASSWORD_PATTERN).matcher(rawPassword).matches();
    }

    @Override
    public String generateRandomPassword(int length) {
        return "";
    }


}
