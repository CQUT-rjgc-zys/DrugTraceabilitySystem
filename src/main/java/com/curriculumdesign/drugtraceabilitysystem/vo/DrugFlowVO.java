package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DrugFlowVO {

    private Integer id;

    private Integer drugId;

    private Integer upstream;

    private Integer downstream;

    private LocalDateTime time;
}
