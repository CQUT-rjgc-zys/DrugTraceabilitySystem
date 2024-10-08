package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugFlowDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugFlowEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DrugFlowMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DrugMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DrugWarehouseMappingMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugFlowService;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugService;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class DrugFlowServiceImpl extends ServiceImpl<DrugFlowMapper, DrugFlowEntity> implements DrugFlowService {

    @Override
    public List<DrugFlowDTO> getDrugFlowsByDrugId(Integer drugId) {
        LambdaQueryWrapper<DrugFlowEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DrugFlowEntity::getDrugId, drugId);
        queryWrapper.orderByAsc(DrugFlowEntity::getTime);
        List<DrugFlowEntity> drugFlowEntities = this.list(queryWrapper);
        return BeanUtil.copyToList(drugFlowEntities, DrugFlowDTO.class);
    }

    @Override
    public void addDrugFlow(DrugFlowDTO drugFlowDTO) {
        DrugFlowEntity drugFlowEntity = BeanUtil.copyProperties(drugFlowDTO, DrugFlowEntity.class);
        drugFlowEntity.setTime(LocalDateTimeUtil.now());
        super.save(drugFlowEntity);
    }
}
