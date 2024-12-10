package xyz.cafebabe.usercenter.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 *  权限数据
 */
@Service
public class StpPermissionServiceImpl implements StpInterface {

    //TODO：用户权限和角色需要缓存

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return Collections.emptyList();
    }
}
