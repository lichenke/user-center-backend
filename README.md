### 用户表
```mysql
create table user
(
    id          bigint auto_increment comment 'id'
        primary key,
    username    varchar(256)                       null comment '用户昵称',
    account     varchar(256)                       not null comment '账号',
    avatar      varchar(4096)                      null comment '用户头像',
    gender      tinyint                            null comment '性别',
    pass        varchar(512)                       not null comment '密码',
    phone       varchar(128)                       null comment '电话',
    email       varchar(512)                       null comment '邮箱',
    user_status int      default 0                 not null comment '状态 0-正常',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除 0-未删除'
)
    comment '用户表';
```

```mysql
CREATE TABLE `permission`
(
`id`             bigint       NOT NULL AUTO_INCREMENT COMMENT '权限ID',
`resource`       varchar(50)  NOT NULL COMMENT '资源/对象',
`action`         varchar(50)  NOT NULL COMMENT '操作类型',
`permission_key` varchar(100) NOT NULL COMMENT '权限标识',
`description`    varchar(200)          DEFAULT NULL COMMENT '权限描述',
`create_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete`     tinyint  default 0                 not null comment '是否删除 0-未删除',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_permission_key` (`permission_key`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';
```

```mysql
CREATE TABLE `role`
(
`id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '角色ID',
`role_key`    varchar(50) NOT NULL COMMENT '角色标识',
`role_name`   varchar(50) NOT NULL COMMENT '角色名称',
`description` varchar(200)         DEFAULT NULL COMMENT '角色描述',
`create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_delete`     tinyint  default 0                 not null comment '是否删除 0-未删除',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_role_key` (`role_key`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';
```

```mysql
CREATE TABLE `user_role`
(
`id`          bigint   NOT NULL AUTO_INCREMENT COMMENT 'ID',
`user_id`     bigint   NOT NULL COMMENT '用户ID',
`role_id`     bigint   NOT NULL COMMENT '角色ID',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
KEY `idx_role_id` (`role_id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色关联表';
```

```mysql
CREATE TABLE `role_permission`
(
`id`            bigint   NOT NULL AUTO_INCREMENT COMMENT 'ID',
`role_id`       bigint   NOT NULL COMMENT '角色ID',
`permission_id` bigint   NOT NULL COMMENT '权限ID',
`create_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`),
KEY `idx_permission_id` (`permission_id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4 COMMENT ='角色权限关联表';
```
