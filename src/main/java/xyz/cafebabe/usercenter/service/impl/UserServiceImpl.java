package xyz.cafebabe.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import xyz.cafebabe.usercenter.mapper.UserMapper;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.service.PasswordService;
import xyz.cafebabe.usercenter.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lichenke
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-11-10 22:42:29
 */
@Service
@Slf4j
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

    @Override
    public User login(String account, String password, HttpServletRequest request) {
        //1. 校验
        if (StringUtils.isAllBlank(account, password)) {
            return null;
        }
        if (account.length() < 4) {
            return null;
        }
        // 密码是否符合要求
        if (!passwordService.isValidPassword(password)) {
            return null;
        }
        // 账号不包含特殊字符
        String regExp = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(regExp).matcher(account);
        if (matcher.find()) {
            // 有特殊字符
            return null;
        }
        // 2. 查询用户是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userAccount", account); // account不应该能重复，所以这个条件就够用了
        User user = userMapper.selectOne(wrapper); // 只有一个，返回多个就报错
        if (user == null || !passwordService.matches(password, user.getUserPassword())) {
            log.info("login failed, password mismatch...");
            return null;
        }
        User safe = new User();
        safe.setId(user.getId());
        safe.setUsername(user.getUsername());
        safe.setUserAccount(user.getUserAccount());
        safe.setAvatarUrl(user.getAvatarUrl());
        safe.setGender(user.getGender());
        safe.setPhone(user.getPhone());
        safe.setEmail(user.getEmail());
        safe.setUserStatus(user.getUserStatus());

        // 4. 记录用户的登录态
        // 客户端连接服务器后，得到一个session状态（匿名会话），返回给前端
        // 用户登录成功后，得到了登录成功的session，并且给该session设置一些值（比如用户信息），返回给前端一个设置cookie的命令
        // 前端接收到后端的命令后，设置cookie，保存到浏览器中
        // 前端再次请求后端的时候（相同域名），在请求头中带上cookie去请求
        // 后端拿到前端传来的cookie，找到对应的session
        // 后端从session中可以取出基于该session存储的变量（用户信息）
        request.getSession().setAttribute("LOGIN_STATUS", safe);
        return safe;
    }

    @Override
    public List<User> list(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(username)) { // 不为空时才拼入条件中
            return Collections.emptyList();
        }
        wrapper.like("username", username);
        return userMapper.selectList(wrapper);
    }

    @Override
    public boolean delete(long id) {
        if (id <= 0) {
            return false;
        }
        return userMapper.deleteById(id) > 0;
    }


}




