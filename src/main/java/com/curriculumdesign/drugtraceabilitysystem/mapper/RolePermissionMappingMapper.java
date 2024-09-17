package com.curriculumdesign.drugtraceabilitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.curriculumdesign.drugtraceabilitysystem.entity.RolePermissionMappingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RolePermissionMappingMapper extends BaseMapper<RolePermissionMappingEntity> {

    @Delete("delete from role_permission_mapping where role_id = #{roleId}")
    void deleteByRoleId(@Param("roleId") Long roleId);

    @Update("update role_permission_mapping set allowed = #{allowed} where id = #{id}")
    void updateAllowedById(@Param("id") Long id, @Param("allowed") Boolean allowed);

    @Select("select * from role_permission_mapping where role_id = #{roleId} order by allowed desc")
    List<RolePermissionMappingEntity> getRolePermissionMappingByRoleId(@Param("roleId") Long roleId);
}
