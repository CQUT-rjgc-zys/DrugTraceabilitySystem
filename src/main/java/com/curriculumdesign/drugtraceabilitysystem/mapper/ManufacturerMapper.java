package com.curriculumdesign.drugtraceabilitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugFlowEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.ManufacturerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManufacturerMapper extends BaseMapper<ManufacturerEntity> {

    @Select("SELECT id FROM manufacturer WHERE name = #{manufacturerName}")
    Integer getIdsByName(@Param("manufacturerName") String manufacturerName);
}
