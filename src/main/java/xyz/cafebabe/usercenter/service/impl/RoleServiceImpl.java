package xyz.cafebabe.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.validation.annotation.Validated;
import xyz.cafebabe.usercenter.model.domain.Role;
import xyz.cafebabe.usercenter.service.RoleService;
import xyz.cafebabe.usercenter.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
* @author lichenke
* @description 针对表【role(角色表)】的数据库操作Service实现
* @createDate 2024-12-11 16:57:12
*/
@Service
@Validated
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<String> getRoles(long userId) {
        return roleMapper.getRoles(userId);
    }
}




