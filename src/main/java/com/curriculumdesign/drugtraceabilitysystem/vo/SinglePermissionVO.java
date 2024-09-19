package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

@Data
public class SinglePermissionVO extends PermissionVO {

    /**
     * 是否允许
     */
    private Boolean allowed;
}
