package com.curriculumdesign.drugtraceabilitysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色id <外键
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码 <加密存储
     */
    @TableField(value = "password")

    private String password;

    /**
     * 电话号码
     */
    @TableField(value = "phone")
    private String phone;
}
