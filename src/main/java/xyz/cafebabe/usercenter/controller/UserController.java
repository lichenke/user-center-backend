package xyz.cafebabe.usercenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.model.domain.request.LoginRequest;
import xyz.cafebabe.usercenter.model.domain.request.RegisterRequest;
import xyz.cafebabe.usercenter.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;


/**
 * 用户控制器
 *
 * @author lichenke
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long register(@RequestBody RegisterRequest request) {
        if (request == null) {
            return null;
        }
        String account = request.getAccount();
        String password = request.getPassword();
        String checkPassword = request.getCheckPassword();
        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            return null;
        }
        return userService.register(account, password, checkPassword);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        if (request == null) {
            return null;
        }
        String account = request.getAccount();
        String password = request.getPassword();
        if (StringUtils.isAnyBlank(account, password)) {
            return null;
        }
        return userService.login(account, password, httpServletRequest);
    }

    @PostMapping("/logout")
    public Boolean logout(HttpServletRequest httpServletRequest) {
        return userService.logout(httpServletRequest);
    }

    @GetMapping("/currentUser")
    public User currentUser(HttpServletRequest httpServletRequest) {
        return userService.currentUser(httpServletRequest);
    }

    @GetMapping("/search")
    public List<User> search(String username, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(username)) {
            return Collections.emptyList();
        }
        return userService.list(username, httpServletRequest);
    }

    @PostMapping("/delete")
    public boolean deleteUser(long userId, HttpServletRequest httpServletRequest) {
        if (userId <= 0) {
            return false;
        }
        return userService.delete(userId, httpServletRequest);
    }


}
