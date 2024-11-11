package xyz.cafebabe.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.service.UserService;
import xyz.cafebabe.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lichenke
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-11-10 22:42:29
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public long register(String account, String password, String checkPassword) {
       return 0;
    }
}




