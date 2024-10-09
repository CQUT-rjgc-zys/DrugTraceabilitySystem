package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugFlowDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.ManufacturerDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugFlowEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.ManufacturerEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DrugFlowMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.ManufacturerMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugFlowService;
import com.curriculumdesign.drugtraceabilitysystem.service.ManufacturerService;
import com.curriculumdesign.drugtraceabilitysystem.vo.ManufacturerVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ManufacturerServiceImpl extends ServiceImpl<ManufacturerMapper, ManufacturerEntity> implements ManufacturerService {

    @Override
    public void addManufacturer(ManufacturerDTO dto) {
        ManufacturerEntity manufacturerEntity = BeanUtil.copyProperties(dto, ManufacturerEntity.class);
        super.save(manufacturerEntity);
    }

    @Override
    public List<ManufacturerVO> getAll() {
        List<ManufacturerEntity> list = list();
        return BeanUtil.copyToList(list, ManufacturerVO.class);
    }

    @Override
    public List<ManufacturerVO> fuzzyQuery(ManufacturerDTO dto) {
        LambdaQueryWrapper<ManufacturerEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ManufacturerEntity::getName, dto.getName());
        wrapper.like(ManufacturerEntity::getLocation, dto.getLocation());
        wrapper.like(ManufacturerEntity::getLicenseNumber, dto.getLicenseNumber());
        wrapper.like(ManufacturerEntity::getPhone, dto.getPhone());
        List<ManufacturerEntity> list = super.list(wrapper);
        return BeanUtil.copyToList(list, ManufacturerVO.class);
    }

    @Override
    public void deleteManufacturer(List<Integer> ids) {
        super.removeByIds(ids);
    }

    @Override
    public void updateManufacturer(ManufacturerDTO dto) {
        ManufacturerEntity manufacturerEntity = BeanUtil.copyProperties(dto, ManufacturerEntity.class);
        super.updateById(manufacturerEntity);
    }
}
