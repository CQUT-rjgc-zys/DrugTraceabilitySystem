package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugFlowDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugFlowEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugFlowVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface DrugFlowService extends IService<DrugFlowEntity> {

    List<DrugFlowVO> getDrugFlowsByDrugId(Integer drugId);

    void addDrugFlow(List<DrugFlowDTO> dtos);

    void exportExcel(Integer id, HttpServletResponse response) throws IOException;

}
