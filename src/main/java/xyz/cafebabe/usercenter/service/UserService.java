package xyz.cafebabe.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.model.domain.request.RegisterRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author lichenke
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2024-11-10 22:42:29
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param registerRequest 注册请求对象
     * @return 用户ID
     */
    long register(RegisterRequest registerRequest);


    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @param request  HttpServletRequest
     * @return 登录的用户
     */
    User login(String account, String password, HttpServletRequest request);


    /**
     * 用户注销
     *
     * @param request HttpServletRequest
     * @return 用户注销成功则返回true，否则返回false
     */
    boolean logout(HttpServletRequest request);

    /**
     * 获取当前登录用户信息（状态）
     *
     * @return 当前登录用户
     */
    User currentUser(HttpServletRequest request);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象列表
     */
    List<User> list(String username, HttpServletRequest request);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 删除成功则返回true，否则返回false
     */
    boolean delete(long id, HttpServletRequest request);

}
