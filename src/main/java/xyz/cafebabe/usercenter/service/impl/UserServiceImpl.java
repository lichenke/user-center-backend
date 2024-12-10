package xyz.cafebabe.usercenter.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import xyz.cafebabe.usercenter.exception.BusinessException;
import xyz.cafebabe.usercenter.exception.ParamInvalidException;
import xyz.cafebabe.usercenter.mapper.UserMapper;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.model.domain.request.LoginRequest;
import xyz.cafebabe.usercenter.model.domain.request.RegisterRequest;
import xyz.cafebabe.usercenter.service.PasswordService;
import xyz.cafebabe.usercenter.service.UserService;

import javax.annotation.Resource;
import java.util.List;

import static xyz.cafebabe.usercenter.common.ResponseCode.PARAM_INVALID_ERROR;
import static xyz.cafebabe.usercenter.constant.UserConstant.ADMIN_ROLE;

/**
 * @author lichenke
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-11-10 22:42:29
 */
@Service
@Slf4j
@Validated
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordService passwordService;

    @Override
    public long register(RegisterRequest registerRequest) {
        String account = registerRequest.getAccount();
        String password = registerRequest.getPassword();
        String checkPassword = registerRequest.getCheckPassword();
        // 密码和校验密码是否相同
        if (!password.equals(checkPassword)) {
            throw new ParamInvalidException(PARAM_INVALID_ERROR, "‘password’和‘checkPassword’不相同");
        }
        // 账户不能有重复
        boolean exists = userMapper.exists(new QueryWrapper<User>().eq("userAccount", account));
        if (exists) {
            throw new BusinessException("用户'" + account + "'已存在");
        }
        // 密码不能明文入库，必须加密
        String encrypted = passwordService.encryptPassword(password);
        // 入库
        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(encrypted);
        userMapper.insert(user);
        return user.getId(); // 返回用户ID
    }

    @Override
    public User login(LoginRequest loginRequest) {
        String account = loginRequest.getAccount();
        String password = loginRequest.getPassword();
        // 查询用户是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userAccount", account); // account不能重复，所以这个条件就够用了
        wrapper.select("id", "username", "userAccount", "avatarUrl", "userPassword",
                "gender", "phone", "email", "userStatus", "userRole");
        User user;
        try {
            user = userMapper.selectOne(wrapper); // 只有一个，返回多个就报错
            if (user == null) {
                throw new BusinessException("用户'" + account + "'不存在");
            }
            if (!passwordService.matches(password, user.getUserPassword())) {
                throw new BusinessException("密码错误");
            }
        } catch (Exception e) {
            throw new BusinessException("登录失败: %s", e, e.getMessage());
        }
        // 使用sa-token
        // -------------------------
        Long userId = user.getId();
        if (StpUtil.isLogin(userId) && StpUtil.getTokenValue() != null) { // 暂时只允许单客户端登录
            throw new BusinessException("该用户已登录，不允许重复登录");
        }
        StpUtil.login(userId);
        // -------------------------
        User safe = new User();
        safe.setId(userId);
        safe.setUsername(user.getUsername());
        safe.setUserAccount(user.getUserAccount());
        safe.setAvatarUrl(user.getAvatarUrl());
        safe.setGender(user.getGender());
        safe.setPhone(user.getPhone());
        safe.setEmail(user.getEmail());
        safe.setUserStatus(user.getUserStatus());
        safe.setUserRole(user.getUserRole());
        // 4. 记录用户的登录态
        // 客户端连接服务器后，得到一个session状态（匿名会话），返回给前端
        // 用户登录成功后，得到了登录成功的session，并且给该session设置一些值（比如用户信息），返回给前端一个设置cookie的命令
        // 前端接收到后端的命令后，设置cookie，保存到浏览器中
        // 前端再次请求后端的时候（相同域名），在请求头中带上cookie去请求
        // 后端拿到前端传来的cookie，找到对应的session
        // 后端从session中可以取出基于该session存储的变量（用户信息）
        // request.getSession().setAttribute(USER_LOGIN_STATUS, safe);
        return safe;
    }

    @Override
    public void logout() {
        // 使用sa-token
        // -------------------------
        if (!StpUtil.isLogin()) {
            throw new BusinessException("当前没有用户登录");
        }
        StpUtil.logout();
        // -------------------------

//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute(USER_LOGIN_STATUS) == null) {
//            throw new BusinessException("当前没有用户登录");
//        }
//        // 根据sessionId移除登录态即可完成用户注销
//        session.removeAttribute(USER_LOGIN_STATUS); // 本地的cookie中sid仍然存在
    }

    @Override
    public User currentUser() {
        // 使用sa-token
        // ----------------
        long loginId = StpUtil.getLoginIdAsLong();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", loginId);
        deIdentify(wrapper);
        return userMapper.selectOne(wrapper);
        // ----------------

//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute(USER_LOGIN_STATUS) == null) {
//            throw new BusinessException("当前没有用户登录");
//        }
//        User user = (User) session.getAttribute(USER_LOGIN_STATUS);
//        // 再查一次库，获取该用户最新的信息
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("id", user.getId());
//        deIdentify(wrapper);
//        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<User> list(String username) {
        // 首先鉴权
        User currentUser = currentUser();
        if (!hasPermission(currentUser)) {
            throw new BusinessException("当前用户没有该操作权限");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username", username);
        deIdentify(wrapper);
        return userMapper.selectList(wrapper);
    }

    @Override
    public boolean delete(long id) {
        // 首先鉴权
        User currentUser = currentUser();
        if (!hasPermission(currentUser)) {
            throw new BusinessException("当前用户没有该操作权限");
        }
        return userMapper.deleteById(id) > 0;
    }

    /**
     * 用户鉴权
     *
     * @param user 用户对象
     * @return 有权限则返回true，否则返回false
     */
    private boolean hasPermission(User user) {
        return user.getUserRole() == ADMIN_ROLE;
    }

    /**
     * 用户信息脱敏
     */
    private void deIdentify(QueryWrapper<User> wrapper) {
        wrapper.select("id", "username", "userAccount", "avatarUrl",
                "gender", "phone", "email", "userStatus", "userRole");
    }

}




