package com.curriculumdesign.drugtraceabilitysystem.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色信息
     */
    private RoleVO roleVO;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;
}
