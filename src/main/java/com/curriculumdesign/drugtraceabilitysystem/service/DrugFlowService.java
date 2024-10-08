package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugFlowDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugFlowEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;

import java.util.List;

public interface DrugFlowService extends IService<DrugFlowEntity> {

    List<DrugFlowDTO> getDrugFlowsByDrugId(Integer drugId);

    void addDrugFlow(DrugFlowDTO drugFlowDTO);
}
