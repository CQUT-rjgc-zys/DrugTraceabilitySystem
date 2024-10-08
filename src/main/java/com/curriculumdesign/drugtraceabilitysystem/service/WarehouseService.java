package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugWarehouseMappingDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.WarehouseDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.WarehouseEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.WarehouseVO;

import java.util.List;

public interface WarehouseService extends IService<WarehouseEntity> {

    WarehouseVO addWarehouse(WarehouseDTO dto);

    void updateWarehouse(WarehouseDTO dto);

    void deleteWarehouse(List<Integer> ids);

    WarehouseVO queryById(Integer id);

    WarehouseVO queryByName(String name);

    void addDrugWarehouseMapping(DrugWarehouseMappingDTO dto);

    void deleteDrugWarehouseMapping(Integer id);

    void updateDrugWarehouseMapping(DrugWarehouseMappingDTO dto);

    List<WarehouseVO> getAll();

    List<WarehouseVO> fuzzyQuery(WarehouseDTO dto);
}
