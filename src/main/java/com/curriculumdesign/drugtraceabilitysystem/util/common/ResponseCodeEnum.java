package com.curriculumdesign.drugtraceabilitysystem.util.common;

/**
 * 响应code值枚举类
 */
public enum ResponseCodeEnum {

    SUCCESS(200, "请求成功"),

    FAIL(500, "请求失败");

    ResponseCodeEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    private final Integer code;

    private final String info;

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
