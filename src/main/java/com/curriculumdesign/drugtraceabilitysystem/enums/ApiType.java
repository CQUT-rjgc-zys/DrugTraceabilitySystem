package com.curriculumdesign.drugtraceabilitysystem.enums;

import lombok.Getter;

/**
 * api类型枚举类
 */
@Getter
public enum ApiType {

    CREATE_API("创建资源接口", 1),

    QUERY_API("查找资源接口", 2),

    DELETE_API("删除资源接口", 3),

    UPDATE_API("更新资源接口", 4);

    ApiType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    private String name;

    private Integer code;

}
