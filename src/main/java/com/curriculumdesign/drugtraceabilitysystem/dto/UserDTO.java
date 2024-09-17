package com.curriculumdesign.drugtraceabilitysystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    private Long id;

    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @Size(max = 20, message = "用户名长度最大为20")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(max = 20, message = "密码长度最大为20")
    private String password;

    @NotBlank(message = "电话不能为空")
    @Size(max = 11, message = "电话长度最大为11")
    private String phone;
}
