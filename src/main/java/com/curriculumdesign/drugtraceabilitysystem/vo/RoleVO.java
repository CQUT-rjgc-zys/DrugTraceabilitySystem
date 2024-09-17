package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

@Data
public class RoleVO {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;
}
