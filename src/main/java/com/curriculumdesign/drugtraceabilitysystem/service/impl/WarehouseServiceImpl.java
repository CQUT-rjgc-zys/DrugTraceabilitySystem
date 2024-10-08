package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugWarehouseMappingDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.WarehouseDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugWarehouseMappingEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.WarehouseEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DrugWarehouseMappingMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.WarehouseMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.WarehouseService;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.WarehouseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, WarehouseEntity> implements WarehouseService {

    @Autowired
    private DrugWarehouseMappingMapper drugWarehouseMappingMapper;

    @Override
    public WarehouseVO addWarehouse(WarehouseDTO dto) {
        WarehouseEntity warehouseEntity = BeanUtil.copyProperties(dto, WarehouseEntity.class);
        this.save(warehouseEntity);
        return BeanUtil.copyProperties(warehouseEntity, WarehouseVO.class);
    }

    @Override
    public void updateWarehouse(WarehouseDTO dto) {
        WarehouseEntity warehouseEntity = BeanUtil.copyProperties(dto, WarehouseEntity.class);
        this.updateById(warehouseEntity);
    }

    @Override
    public void deleteWarehouse(List<Integer> ids) {
        this.removeBatchByIds(ids);
    }

    @Override
    public WarehouseVO queryById(Integer id) {
        WarehouseEntity warehouseEntity = super.getById(id);
        return BeanUtil.copyProperties(warehouseEntity, WarehouseVO.class);
    }

    @Override
    public WarehouseVO queryByName(String name) {
        LambdaQueryWrapper<WarehouseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WarehouseEntity::getName, name);
        WarehouseEntity warehouseEntity = super.getOne(wrapper);
        return BeanUtil.copyProperties(warehouseEntity, WarehouseVO.class);
    }

    @Override
    public void addDrugWarehouseMapping(DrugWarehouseMappingDTO dto) {
        DrugWarehouseMappingEntity drugWarehouseMappingEntity = BeanUtil.copyProperties(dto, DrugWarehouseMappingEntity.class);
        drugWarehouseMappingMapper.insert(drugWarehouseMappingEntity);
    }

    @Override
    public void deleteDrugWarehouseMapping(Integer id) {
        drugWarehouseMappingMapper.deleteById(id);
    }

    @Override
    public void updateDrugWarehouseMapping(DrugWarehouseMappingDTO dto) {
        DrugWarehouseMappingEntity drugWarehouseMappingEntity = BeanUtil.copyProperties(dto, DrugWarehouseMappingEntity.class);
        drugWarehouseMappingMapper.updateById(drugWarehouseMappingEntity);
    }

    @Override
    public List<WarehouseVO> getAll() {
        List<WarehouseEntity> list = super.list();
        return BeanUtil.copyToList(list, WarehouseVO.class);
    }

    @Override
    public List<WarehouseVO> fuzzyQuery(WarehouseDTO dto) {
        LambdaQueryWrapper<WarehouseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(WarehouseEntity::getName, dto.getName());
        wrapper.like(WarehouseEntity::getLocation, dto.getLocation());
        wrapper.like(WarehouseEntity::getPhone, dto.getPhone());
        List<WarehouseEntity> list = super.list(wrapper);
        return BeanUtil.copyToList(list, WarehouseVO.class);
    }

}
