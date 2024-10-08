package com.curriculumdesign.drugtraceabilitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.curriculumdesign.drugtraceabilitysystem.entity.DistributorEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.ManufacturerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DistributorMapper extends BaseMapper<DistributorEntity> {

    @Select("SELECT id FROM distributor WHERE name = #{distributorName}")
    Integer getIdsByName(@Param("distributorName") String distributorName);
}
