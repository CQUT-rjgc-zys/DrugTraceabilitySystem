package com.curriculumdesign.drugtraceabilitysystem.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * 模型转换工具类
 */
public class ModelTransformation {

    private static final Logger log = LoggerFactory.getLogger(ModelTransformation.class);

    public static <T> T transform(Object obj, Class<T> clazz) {
        if (obj == null) {
            return null;
        }
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(obj, instance);
            return instance;
        } catch (Exception e) {
            log.error("模型转换异常，失败原因：{}", e.getMessage());
            return null;
        }
    }
}
