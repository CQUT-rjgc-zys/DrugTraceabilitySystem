package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;

import java.util.List;

public interface DrugService extends IService<DrugEntity> {

    DrugVO addDrug(DrugDTO dto);

    void deleteDrug(List<Integer> ids);

    void updateDrug(DrugDTO dto);

    DrugVO queryDrugByName(String name);

    DrugVO queryDrugById(Integer id);

    List<DrugVO> queryDrugsByStatus(Integer status);

    List<DrugVO> getAllDrugs();

    List<DrugVO> fuzzyQueryDrugs(DrugDTO dto);
}
