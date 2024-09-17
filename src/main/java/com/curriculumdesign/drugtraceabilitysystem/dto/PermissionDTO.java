package com.curriculumdesign.drugtraceabilitysystem.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PermissionDTO {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * api接口路径
     */
    @NotBlank(message = "api接口路径不能为空")
    @Size(max = 50, message = "api接口路径不能超过50个字符")
    private String apiUrl;

    /**
     * api接口描述
     */
    @Size(max = 50, message = "api接口描述不能超过50个字符")
    private String apiDesc;

    /**
     * api接口类型
     */
    @Max(value = 4, message = "api接口类型只能为1-4")
    @NotNull(message = "api接口类型不能为空")
    private Integer apiType;
}
