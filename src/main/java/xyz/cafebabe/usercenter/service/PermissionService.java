package xyz.cafebabe.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.cafebabe.usercenter.model.domain.Permission;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author lichenke
 * @description 针对表【permission(权限表)】的数据库操作Service
 * @createDate 2024-12-11 16:50:06
 */
public interface PermissionService extends IService<Permission> {

    List<String> getPermissions(@Min(value = 1, message = "'userId'不能小于1") long userId);
}
