package xyz.cafebabe.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import xyz.cafebabe.usercenter.mapper.UserMapper;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.service.PasswordService;
import xyz.cafebabe.usercenter.service.UserService;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lichenke
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-11-10 22:42:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordService passwordService;

    @Override
    public long register(String account, String password, String checkPassword) {
        //1. 校验
        if (StringUtils.isAllBlank(account, password, checkPassword)) {
            return -1;
        }
        if (account.length() < 4) {
            return -1;
        }
        // 密码和校验密码是否相同
        if (!password.equals(checkPassword)) {
            return -1;
        }
        // 密码是否符合要求
        if (!passwordService.isValidPassword(password)) {
            return -1;
        }
        // 账号不包含特殊字符
        String regExp = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(regExp).matcher(account);
        if (matcher.find()) {
            // 有特殊字符
            return -1;
        }
        // 账户不能有重复
        Long count = userMapper.selectCount(new QueryWrapper<User>().eq("userAccount", account));
        if (count > 0) {
            return -1;
        }
        // 密码不能明文入库，必须加密
        String encrypted = passwordService.encryptPassword(password);
        // 入库
        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(encrypted);
        int res = userMapper.insert(user);
        if (res != 1) {
            return -1;
        }
        return user.getId(); // 返回用户ID
    }
}




