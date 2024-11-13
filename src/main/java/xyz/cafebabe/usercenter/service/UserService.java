package xyz.cafebabe.usercenter.service;

import xyz.cafebabe.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author lichenke
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-11-10 22:42:29
*/
public interface UserService extends IService<User> {

    /**
     * 用户注释
     *
     * @param account 账号
     * @param password 密码
     * @param checkPassword 校验密码
     * @return 用户ID
     */
    long register(String account, String password, String checkPassword);


    User login(String account, String password, HttpServletRequest request);

}
