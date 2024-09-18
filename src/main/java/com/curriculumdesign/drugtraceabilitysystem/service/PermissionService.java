package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.PermissionDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.PermissionEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.PermissionVO;

import java.util.List;

public interface PermissionService extends IService<PermissionEntity> {

    PermissionVO addPermission(PermissionDTO dto);

    void deletePermissionById(Long id);

    void updatePermission(PermissionDTO dto);

    List<PermissionVO> getPermissionList();
}
