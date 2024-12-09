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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

// 所有的BaseResponse封装只在controller层进行

/**
 * 用户控制器
 *
 * @author lichenke
 */
@RestController
@Slf4j
@Validated
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
        User user = userService.login(request, httpServletRequest);
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
    public BaseResponse<List<User>> search(@NotBlank(message = "'username'不能为空") String username, HttpServletRequest httpServletRequest) {
        List<User> list = userService.list(username, httpServletRequest);
        return BaseResponse.success(list);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@Min(value = 1, message = "'id'不能小于1") long userId, HttpServletRequest httpServletRequest) {
        boolean result = userService.delete(userId, httpServletRequest);
        return BaseResponse.success(result);
    }


}
