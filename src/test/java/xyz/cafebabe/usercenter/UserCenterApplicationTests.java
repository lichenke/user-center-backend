package xyz.cafebabe.usercenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.service.UserService;

import javax.annotation.Resource;

@SpringBootTest
class UserCenterApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("lichenke");
        user.setUserAccount("cafebabe");
        user.setAvatarUrl("111111");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("234");
        boolean result = userService.save(user);
        Assertions.assertTrue(result);
    }
}
