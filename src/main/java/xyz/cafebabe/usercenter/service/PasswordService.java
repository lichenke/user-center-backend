package xyz.cafebabe.usercenter.service;

public interface PasswordService {

    // BCrypt 强度参数，越大越安全但性能消耗越大，推荐10-12
    int BCRYPT_STRENGTH = 12;

    // 密码复杂度正则表达式
    /**
     * 至少8个字符长度
     * 至少包含一个数字
     * 至少包含一个小写字母
     * 至少包含一个大写字母
     * 至少包含一个特殊字符（@#$%^&+=）
     * 不能包含空白字符
     *
     */
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    /**
     * 加密密码
     *
     * @param rawPassword 明文密码
     * @return 加密后的密码
     */
    String encryptPassword(String rawPassword);

    /**
     * 验证明文密码和加密后的密码是否能够对应
     *
     * @param rawPassword 明文密码
     * @param encodedPassword 加密后的密码
     * @return 如何能够对应则返回true，反之返回false
     */
    boolean matches(String rawPassword, String encodedPassword);

    /**
     * 验证密码的复杂度是否合规
     *
     * @param rawPassword 明文密码
     * @return 合规则返回true，反之返回false
     */
    boolean isValid(String rawPassword);

    /**
     * 生成随机密码
     *
     * @param length 随机密码位数
     * @return 生成的明文随机密码
     */
    String generateRandomPassword(int length);
}
