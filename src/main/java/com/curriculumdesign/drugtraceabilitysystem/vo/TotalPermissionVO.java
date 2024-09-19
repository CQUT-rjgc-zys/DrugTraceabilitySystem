package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

@Data
public class TotalPermissionVO extends PermissionVO {

    /**
     * 是否已使用
     */
    private Boolean used;
}
