package com.curriculumdesign.drugtraceabilitysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.curriculumdesign.drugtraceabilitysystem.enums.ApiType;
import lombok.Data;

@Data
@TableName("permission")
public class PermissionEntity {

    /**
     * 权限ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * api接口路径
     */
    @TableField(value = "api_url")
    private String apiUrl;

    /**
     * api接口描述
     */
    @TableField(value = "api_desc")
    private String apiDesc;

    /**
     * api接口类型
     */
    @TableField(value = "api_type")
    private Integer apiType;
}
