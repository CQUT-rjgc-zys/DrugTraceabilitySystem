package com.curriculumdesign.drugtraceabilitysystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RoleDTO {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色编号
     */
    @NotBlank(message = "角色编号不能为空")
    private String roleCode;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    @Size(max = 200, message = "角色描述不能超过200字")
    private String roleDesc;
}
