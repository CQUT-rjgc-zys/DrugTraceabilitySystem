package com.curriculumdesign.drugtraceabilitysystem.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class RolePermissionMappingDTO {

    /**
     * 映射ID
     */
    @NotNull(message = "映射ID不能为空")
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
    @NotNull(message = "是否允许不能为空")
    private Boolean allowed;
}
