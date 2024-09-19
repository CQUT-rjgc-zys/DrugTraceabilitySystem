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

}
