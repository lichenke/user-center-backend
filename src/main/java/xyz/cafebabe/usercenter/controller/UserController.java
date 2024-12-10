package xyz.cafebabe.usercenter.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.cafebabe.usercenter.common.BaseResponse;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.model.domain.request.LoginRequest;
import xyz.cafebabe.usercenter.model.domain.request.RegisterRequest;
import xyz.cafebabe.usercenter.service.UserService;

import javax.annotation.Resource;
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
    public BaseResponse<User> login(@Validated @RequestBody LoginRequest request) {
        User user = userService.login(request);
        return BaseResponse.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<?> logout() {
        userService.logout();
        return BaseResponse.success();
    }

    @GetMapping("/currentUser")
    public BaseResponse<User> currentUser() {
        User user = userService.currentUser();
        return BaseResponse.success(user);
    }

    @SaCheckPermission(value = "user:list", orRole = "admin")
    @GetMapping("/search")
    public BaseResponse<List<User>> search(@NotBlank(message = "'username'不能为空") String username) {
        List<User> list = userService.list(username);
        return BaseResponse.success(list);
    }

    @SaCheckPermission(value = "user:delete", orRole = "admin")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@Min(value = 1, message = "'id'不能小于1") long userId) {
        boolean result = userService.delete(userId);
        return BaseResponse.success(result);
    }


}
