package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

@Data
public class PermissionVO {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * api接口路径
     */
    private String apiUrl;

    /**
     * api接口描述
     */
    private String apiDesc;

    /**
     * api接口类型
     */
    private Integer apiType;

    /**
     * 是否已使用
     * 0：未使用
     * 1：已使用
     * 默认为0
     */
    private Boolean isUsed;
}
