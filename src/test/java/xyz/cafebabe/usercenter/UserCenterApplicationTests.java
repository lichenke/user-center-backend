package xyz.cafebabe.usercenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.cafebabe.usercenter.model.domain.User;
import xyz.cafebabe.usercenter.service.PasswordService;
import xyz.cafebabe.usercenter.service.UserService;

import javax.annotation.Resource;

@SpringBootTest
class UserCenterApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private PasswordService passwordService;

    @Test
    void testAddUser() {
        // 任何一个参数为空
        String account = "";
        String password = "123456";
        String check = "123456";
        long res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        account = "lichenke";
        password = "";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        password = "123456";
        check = "";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        // account < 4
        account = "hh";
        password = "123456";
        check = "123456";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        // 密码和校验密码不同
        account = "lichenke";
        password = "123456";
        check = "12345";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        // 密码不符合要求
        account = "lichenke";
        password = "123456";
        check = "123456";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        // 账号包含特殊字符
        account = "lich@enke";
        password = "1qa@WS3ed";
        check = "1qa@WS3ed";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        // 账号有重复
        account = "cafebabe";
        password = "1qa@WS3ed";
        check = "1qa@WS3ed";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        account = "cafebabe";
        password = "1qa@WS3ed";
        check = "1qa@WS3ed";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res < 0);

        account = "lichenke";
        password = "1qa@WS3ed";
        check = "1qa@WS3ed";
        res = userService.register(account, password, check);
        Assertions.assertTrue(res > 0);
    }


    @Test
    void testPassword() {
        String encrypted = "$2a$12$ixjreJz5vhpb1C3troPL5OlQkjYYc9xOF//zRBirLh2l8OI0pwFa6";
        String raw = "1qa@WS3ed";
        boolean matches = passwordService.matches(raw, encrypted);
        Assertions.assertTrue(matches);
    }
}
