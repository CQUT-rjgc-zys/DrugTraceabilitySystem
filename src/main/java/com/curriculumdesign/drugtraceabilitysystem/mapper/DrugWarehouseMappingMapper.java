package com.curriculumdesign.drugtraceabilitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugWarehouseMappingEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DrugWarehouseMappingMapper extends BaseMapper<DrugWarehouseMappingEntity> {

    @Select("select drug_id from drug_warehouse_mapping group by drug_id")
    List<Integer> getDrugIds();
}
