package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugFlowDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.ManufacturerDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugFlowEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.ManufacturerEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.ManufacturerVO;

import java.util.List;

public interface ManufacturerService extends IService<ManufacturerEntity> {

    void addManufacturer(ManufacturerDTO dto);

    List<ManufacturerVO> getAll();

    List<ManufacturerVO> fuzzyQuery(ManufacturerDTO dto);

    void deleteManufacturer(List<Integer> ids);

    void updateManufacturer(ManufacturerDTO dto);
}
