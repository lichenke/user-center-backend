package xyz.cafebabe.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.cafebabe.usercenter.mapper.PermissionMapper;
import xyz.cafebabe.usercenter.model.domain.Permission;
import xyz.cafebabe.usercenter.service.PermissionService;
import org.springframework.stereotype.Service;

/**
* @author lichenke
* @description 针对表【permission(权限表)】的数据库操作Service实现
* @createDate 2024-12-11 16:50:06
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




