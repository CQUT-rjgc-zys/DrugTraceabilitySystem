package com.curriculumdesign.drugtraceabilitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugFlowEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrugFlowMapper extends BaseMapper<DrugFlowEntity> {
}
