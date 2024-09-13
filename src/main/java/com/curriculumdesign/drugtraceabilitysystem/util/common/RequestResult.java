package com.curriculumdesign.drugtraceabilitysystem.util.common;

import lombok.Data;

/**
 * 请求返回信息封装类
 */
@Data
public class RequestResult<T> {

    private final Integer code;

    private final T data;

    private final String message;

    RequestResult(Integer code, T data,String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    RequestResult(ResponseCodeEnum code) {
        this.code = code.getCode();
        this.data = null;
        this.message = code.getInfo();
    }

    /**
     * 请求成功的返回信息（不带参数）
     */
    public static <T> RequestResult<T> success() {
        return new RequestResult<>(ResponseCodeEnum.SUCCESS);
    }

    /**
     * 请求失败的返回信息（不带参数）
     */
    public static <T> RequestResult<T> fail() {
        return new RequestResult<>(ResponseCodeEnum.FAIL);
    }

    /**
     * 请求失败的返回信息（带参数）
     */
    public static <T> RequestResult<T> fail(String message) {
        return new RequestResult<>(ResponseCodeEnum.FAIL.getCode(), null, message);
    }

}
