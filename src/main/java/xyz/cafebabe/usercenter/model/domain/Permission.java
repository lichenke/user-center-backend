package xyz.cafebabe.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 权限表
 * @TableName permission
 */
@TableName(value ="permission")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permission implements Serializable {
    /**
     * 权限ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 资源/对象
     */
    private String resource;

    /**
     * 操作类型
     */
    private String action;

    /**
     * 权限标识
     */
    private String permissionKey;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0-未删除
     */
    @TableLogic
    private Integer is_delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}