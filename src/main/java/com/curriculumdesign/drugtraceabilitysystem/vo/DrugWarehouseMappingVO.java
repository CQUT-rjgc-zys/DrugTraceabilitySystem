package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DrugWarehouseMappingVO {

    private Integer id;

    private Integer drugId;

    private Integer warehouseId;

    private LocalDateTime entryDate;

    private LocalDateTime exitDate;
}
