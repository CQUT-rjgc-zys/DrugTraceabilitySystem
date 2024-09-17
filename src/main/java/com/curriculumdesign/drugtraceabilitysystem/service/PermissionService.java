package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.PermissionDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.PermissionEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.PermissionVO;

public interface PermissionService extends IService<PermissionEntity> {

    PermissionVO addPermission(PermissionDTO dto);
}
