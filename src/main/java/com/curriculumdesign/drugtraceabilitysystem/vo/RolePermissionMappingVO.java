package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RolePermissionMappingVO {

    /**
     * 映射ID
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 是否允许
     */
    private Boolean allowed;
}
