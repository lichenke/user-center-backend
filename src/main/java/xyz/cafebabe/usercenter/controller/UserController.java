package xyz.cafebabe.usercenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.cafebabe.usercenter.common.BaseResponse;
import xyz.cafebabe.usercenter.exception.ParamException;
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
    public BaseResponse<Long> register(@RequestBody RegisterRequest request) {
        if (request == null) {
            throw ParamException.nullParamException(RegisterRequest.getClassName());
        }
        String account = request.getAccount();
        String password = request.getPassword();
        String checkPassword = request.getCheckPassword();
        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            throw ParamException.hasBlankParamException("account", "password", "checkPassword");
        }
        long id = userService.register(account, password, checkPassword);
        return BaseResponse.success(id);
    }

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        if (request == null) {
            throw ParamException.nullParamException(LoginRequest.getClassName());
        }
        String account = request.getAccount();
        String password = request.getPassword();
        if (StringUtils.isAnyBlank(account, password)) {
            throw ParamException.hasBlankParamException("account", "password");
        }
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
        if (StringUtils.isBlank(username)) {
            throw ParamException.blankParamException("username");
        }
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
