package xyz.cafebabe.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import xyz.cafebabe.usercenter.mapper.PermissionMapper;
import xyz.cafebabe.usercenter.model.domain.Permission;
import xyz.cafebabe.usercenter.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lichenke
 * @description 针对表【permission(权限表)】的数据库操作Service实现
 * @createDate 2024-12-11 16:50:06
 */
@Service
@Validated
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<String> getPermissions(long userId) {
        return permissionMapper.getPermissions(userId);
    }
}




