package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.DistributorDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.ManufacturerDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DistributorEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.ManufacturerEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DistributorMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.ManufacturerMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.DistributorService;
import com.curriculumdesign.drugtraceabilitysystem.service.ManufacturerService;
import com.curriculumdesign.drugtraceabilitysystem.vo.DistributorVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.ManufacturerVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DistributorServiceImpl extends ServiceImpl<DistributorMapper, DistributorEntity> implements DistributorService {

    @Override
    public void addDistributor(DistributorDTO dto) {
        DistributorEntity distributorEntity = BeanUtil.copyProperties(dto, DistributorEntity.class);
        super.save(distributorEntity);
    }

    @Override
    public List<DistributorVO> getAll() {
        List<DistributorEntity> list = list();
        return BeanUtil.copyToList(list, DistributorVO.class);
    }

    @Override
    public List<DistributorVO> fuzzyQuery(DistributorDTO dto) {
        LambdaQueryWrapper<DistributorEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(DistributorEntity::getName, dto.getName());
        wrapper.like(DistributorEntity::getLocation, dto.getLocation());
        wrapper.like(DistributorEntity::getLicenseNumber, dto.getLicenseNumber());
        wrapper.like(DistributorEntity::getPhone, dto.getPhone());
        List<DistributorEntity> list = super.list(wrapper);
        return BeanUtil.copyToList(list, DistributorVO.class);
    }

    @Override
    public void deleteDistributor(List<Integer> ids) {
        super.removeByIds(ids);
    }
}
