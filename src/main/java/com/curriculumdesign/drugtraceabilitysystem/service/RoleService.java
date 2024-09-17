package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.RoleDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.RoleEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.RoleVO;

import java.util.List;

public interface RoleService extends IService<RoleEntity> {

    List<RoleVO> getRoles();

    RoleVO addRole(RoleDTO dto);

    boolean checkRoleCodeIsExist(String code);

    void deleteRoleById(Long id);

    void updateRole(RoleDTO dto);

    RoleVO getRoleById(Long id);

    List<RoleVO> fuzzyQueryRoleByCode(String code);

    List<RoleVO> fuzzyQueryRoleByName(String name);
}
