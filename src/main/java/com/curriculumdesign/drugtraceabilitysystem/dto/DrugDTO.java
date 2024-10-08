package com.curriculumdesign.drugtraceabilitysystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DrugDTO {

    private Integer id;

    private String manufacturerName;

    private String name;

    private Integer batchNumber;

    private LocalDateTime productionDate;

    private LocalDateTime expiryDate;

    private String status;
}
