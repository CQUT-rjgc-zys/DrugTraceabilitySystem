package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.DistributorDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.ManufacturerDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DistributorEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.ManufacturerEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.DistributorVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.ManufacturerVO;

import java.util.List;

public interface DistributorService extends IService<DistributorEntity> {

    void addDistributor(DistributorDTO dto);

    List<DistributorVO> getAll();

    List<DistributorVO> fuzzyQuery(DistributorDTO dto);

    void deleteDistributor(List<Integer> ids);
}
