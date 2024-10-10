package com.curriculumdesign.drugtraceabilitysystem.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DrugFlowVO {

    private Integer id;

    private Integer drugId;

    private DistributorVO distributor;

    private Integer index;

    private String time;
}
