package xyz.cafebabe.usercenter.mapper;

import xyz.cafebabe.usercenter.model.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author lichenke
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2024-12-11 16:57:12
* @Entity xyz.cafebabe.usercenter.model.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<String> getRoles(Long userId);

}




