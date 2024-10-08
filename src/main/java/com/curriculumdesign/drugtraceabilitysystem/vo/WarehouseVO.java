package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarehouseVO {

    private Integer id;

    private String name;

    private String location;

    private String phone;
}
