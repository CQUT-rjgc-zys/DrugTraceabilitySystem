package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.*;
import com.curriculumdesign.drugtraceabilitysystem.mapper.*;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugService;
import com.curriculumdesign.drugtraceabilitysystem.service.ManufacturerService;
import com.curriculumdesign.drugtraceabilitysystem.service.WarehouseService;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.ManufacturerVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.WarehouseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class DrugServiceImpl extends ServiceImpl<DrugMapper, DrugEntity> implements DrugService {

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ManufacturerMapper manufacturerMapper;

    @Autowired
    private DrugWarehouseMappingMapper drugWarehouseMappingMapper;

    @Autowired
    private WarehouseService warehouseService;

    @Override
    public DrugVO addDrug(DrugDTO dto) {
        DrugEntity entity = BeanUtil.copyProperties(dto, DrugEntity.class);
        entity.setStatus(0);
        String manufacturerName = dto.getManufacturerName();
        Integer manufacturerId = manufacturerMapper.getIdsByName(manufacturerName);
        entity.setManufacturerId(manufacturerId);
        super.save(entity);
        return BeanUtil.copyProperties(entity, DrugVO.class);
    }

    @Override
    public void deleteDrug(List<Integer> ids) {
        super.removeByIds(ids);
    }

    @Override
    public void updateDrug(DrugDTO dto) {
        DrugEntity entity = BeanUtil.copyProperties(dto, DrugEntity.class);
        super.updateById(entity);
    }

    @Override
    public DrugVO queryDrugByName(String name) {
        LambdaQueryWrapper<DrugEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DrugEntity::getName, name);
        DrugEntity entity = super.getOne(wrapper);
        return BeanUtil.copyProperties(entity, DrugVO.class);
    }

    @Override
    public DrugVO queryDrugById(Integer id) {
        DrugEntity entity = super.getById(id);
        return BeanUtil.copyProperties(entity, DrugVO.class);
    }

    @Override
    public List<DrugVO> queryDrugsByStatus(Integer status) {
        if (status == 2)  { // 全部查询
            List<DrugEntity> list = super.list();
            return BeanUtil.copyToList(list, DrugVO.class);
        } else if (status == 0) {
            List<DrugEntity> list = super.list();
            List<Integer> ids = drugWarehouseMappingMapper.getDrugIds();
            List<DrugEntity> results = list.stream().filter(drug -> !ids.contains(drug.getId())).collect(Collectors.toList());
            return BeanUtil.copyToList(results, DrugVO.class);
        } else {
            List<DrugEntity> list = super.list();
            List<Integer> ids = drugWarehouseMappingMapper.getDrugIds();
            List<DrugEntity> results = list.stream().filter(drug -> ids.contains(drug.getId())).collect(Collectors.toList());
            return BeanUtil.copyToList(results, DrugVO.class);
        }
    }

    @Override
    public List<DrugVO> getAllDrugs() {
        List<DrugEntity> list = super.list();
        Map<Integer, DrugEntity> collect = list.stream().collect(Collectors.toMap(DrugEntity::getId, Function.identity()));
        List<DrugVO> drugVOS = BeanUtil.copyToList(list, DrugVO.class);
        for (DrugVO drugVO : drugVOS) {
            DrugEntity entity = collect.get(drugVO.getId());
            Integer status = entity.getStatus();
            if (status == 0) {
                drugVO.setStatus("待售");
            } else if (status == 1) {
                drugVO.setStatus("售卖中");
            } else if (status == 2) {
                drugVO.setStatus("停售");
            }
            Integer manufacturerId = entity.getManufacturerId();
            ManufacturerEntity manufacturerEntity = manufacturerService.getById(manufacturerId);
            Integer warehouseId = entity.getWarehouseId();
            WarehouseEntity warehouseEntity = warehouseService.getById(warehouseId);
            drugVO.setManufacturer(BeanUtil.copyProperties(manufacturerEntity, ManufacturerVO.class));
            drugVO.setWarehouse(BeanUtil.copyProperties(warehouseEntity, WarehouseVO.class));
            LocalDateTime productionDate = entity.getProductionDate();
            LocalDateTime expiryDate = entity.getExpiryDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日");
            drugVO.setProductionDate(productionDate.format(formatter));
            drugVO.setExpiryDate(expiryDate.format(formatter));
        }
        return drugVOS;
    }

    @Override
    public List<DrugVO> fuzzyQueryDrugs(DrugDTO dto) {
        String status = dto.getStatus();
        LambdaQueryWrapper<DrugEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(DrugEntity::getName, dto.getName());
        if (status != null) {
            if (status.equals("售卖中")) {
                wrapper.eq(DrugEntity::getStatus, 0);
            } else if (status.equals("停售")) {
                wrapper.eq(DrugEntity::getStatus, 1);
            }
        }
        List<DrugEntity> list = list(wrapper);
        Map<Integer, DrugEntity> collect = list.stream().collect(Collectors.toMap(DrugEntity::getId, Function.identity()));
        List<DrugVO> drugVOS = BeanUtil.copyToList(list, DrugVO.class);
        for (DrugVO drugVO : drugVOS) {
            DrugEntity entity = collect.get(drugVO.getId());
            Integer statusInt = entity.getStatus();
            if (statusInt == 0) {
                drugVO.setStatus("待售");
            } else if (statusInt == 1) {
                drugVO.setStatus("售卖中");
            } else if (statusInt == 2) {
                drugVO.setStatus("停售");
            }
            Integer manufacturerId = entity.getManufacturerId();
            ManufacturerEntity manufacturerEntity = manufacturerService.getById(manufacturerId);
            Integer warehouseId = entity.getWarehouseId();
            WarehouseEntity warehouseEntity = warehouseService.getById(warehouseId);
            drugVO.setManufacturer(BeanUtil.copyProperties(manufacturerEntity, ManufacturerVO.class));
            drugVO.setWarehouse(BeanUtil.copyProperties(warehouseEntity, WarehouseVO.class));
            LocalDateTime productionDate = entity.getProductionDate();
            LocalDateTime expiryDate = entity.getExpiryDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日");
            drugVO.setProductionDate(productionDate.format(formatter));
            drugVO.setExpiryDate(expiryDate.format(formatter));
        }
        return drugVOS;
    }
}
