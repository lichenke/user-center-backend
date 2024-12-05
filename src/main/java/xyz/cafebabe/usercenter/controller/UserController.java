package xyz.cafebabe.usercenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.cafebabe.usercenter.common.BaseResponse;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.model.domain.request.LoginRequest;
import xyz.cafebabe.usercenter.model.domain.request.RegisterRequest;
import xyz.cafebabe.usercenter.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

// 所有的BaseResponse封装只在controller层进行

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
    public BaseResponse<Long> register(@Validated @RequestBody RegisterRequest request) {
        long userId = userService.register(request);
        return BaseResponse.success(userId);
    }

    @PostMapping("/login")
    public BaseResponse<User> login(@Validated @RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        String account = request.getAccount();
        String password = request.getPassword();
        User user = userService.login(account, password, httpServletRequest);
        return BaseResponse.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Boolean> logout(HttpServletRequest httpServletRequest) {
        boolean result = userService.logout(httpServletRequest);
        return BaseResponse.success(result);
    }

    @GetMapping("/currentUser")
    public BaseResponse<User> currentUser(HttpServletRequest httpServletRequest) {
        User user = userService.currentUser(httpServletRequest);
        return BaseResponse.success(user);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> search(String username, HttpServletRequest httpServletRequest) {
        List<User> list = userService.list(username, httpServletRequest);
        return BaseResponse.success(list);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(long userId, HttpServletRequest httpServletRequest) {
        if (userId <= 0) {
            return null;
        }
        boolean result = userService.delete(userId, httpServletRequest);
        return BaseResponse.success(result);
    }


}
