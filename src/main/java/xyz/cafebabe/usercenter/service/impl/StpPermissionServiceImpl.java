package xyz.cafebabe.usercenter.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Service;
import xyz.cafebabe.usercenter.service.PermissionService;
import xyz.cafebabe.usercenter.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限数据
 */
@Service
public class StpPermissionServiceImpl implements StpInterface {

    //TODO：用户权限和角色需要缓存

    @Resource
    private PermissionService permissionService;

    @Resource
    private RoleService roleService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return permissionService.getPermissions(Long.parseLong((String) loginId));
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return roleService.getRoles(Long.parseLong((String) loginId));
    }
}
