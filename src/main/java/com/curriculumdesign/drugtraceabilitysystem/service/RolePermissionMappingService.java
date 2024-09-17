package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.RolePermissionMappingDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.RolePermissionMappingEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.RolePermissionMappingVO;

import java.util.List;

public interface RolePermissionMappingService extends IService<RolePermissionMappingEntity> {

    void updateStatus(RolePermissionMappingDTO dto);

    List<RolePermissionMappingVO> getRolePermissionMappingByRoleId(Long id);
}
