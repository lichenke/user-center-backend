package xyz.cafebabe.usercenter.service;

import xyz.cafebabe.usercenter.model.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
* @author lichenke
* @description 针对表【role(角色表)】的数据库操作Service
* @createDate 2024-12-11 16:57:12
*/
public interface RoleService extends IService<Role> {

    List<String> getRoles(@Min(value = 1, message = "'userId'不能小于1") long userId);
}
