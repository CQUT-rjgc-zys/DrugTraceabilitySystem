package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.PermissionDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.PermissionEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.RoleEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.RolePermissionMappingEntity;
import com.curriculumdesign.drugtraceabilitysystem.enums.ApiType;
import com.curriculumdesign.drugtraceabilitysystem.mapper.PermissionMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.RoleMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.PermissionService;
import com.curriculumdesign.drugtraceabilitysystem.service.RolePermissionMappingService;
import com.curriculumdesign.drugtraceabilitysystem.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {

    @Autowired
    private PermissionMapper mapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMappingService rolePermissionMappingService;

    @Override
    public PermissionVO addPermission(PermissionDTO dto) {
        PermissionEntity saveEntity = BeanUtil.copyProperties(dto, PermissionEntity.class);
        super.save(saveEntity);
        // 创建权限和角色的关联关系
        List<RoleEntity> roleEntities = roleMapper.selectList(null);
        List<RolePermissionMappingEntity> mappingEntities = new ArrayList<>();
        for (RoleEntity roleEntity : roleEntities) {
            RolePermissionMappingEntity mappingEntity = new RolePermissionMappingEntity();
            mappingEntity
                    .setRoleId(roleEntity.getId())
                    .setPermissionId(saveEntity.getId())
                    .setAllowed(false);
            mappingEntities.add(mappingEntity);
        }
        rolePermissionMappingService.saveBatch(mappingEntities);
        return BeanUtil.copyProperties(saveEntity, PermissionVO.class);
    }
}
