package com.curriculumdesign.drugtraceabilitysystem.dto;

import com.curriculumdesign.drugtraceabilitysystem.vo.DistributorVO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DrugFlowDTO {

    private Integer id;

    private Integer drugId;

    private Integer distributorId;

    private Integer index;

    private LocalDateTime time;
}
