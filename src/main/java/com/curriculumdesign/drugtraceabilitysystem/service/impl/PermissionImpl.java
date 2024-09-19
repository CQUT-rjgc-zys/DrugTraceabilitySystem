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
import com.curriculumdesign.drugtraceabilitysystem.mapper.RolePermissionMappingMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.PermissionService;
import com.curriculumdesign.drugtraceabilitysystem.service.RolePermissionMappingService;
import com.curriculumdesign.drugtraceabilitysystem.vo.PermissionVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.TotalPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {

    @Autowired
    private PermissionMapper mapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMappingService rolePermissionMappingService;

    @Autowired
    private RolePermissionMappingMapper rolePermissionMappingMapper;

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

    @Override
    public void deletePermissionById(Long id) {
        // 判断是否存在此权限信息
        PermissionEntity permissionEntity = super.getById(id);
        if (Objects.isNull(permissionEntity)) {
            throw new IllegalArgumentException("不存在id为: " + id + "的权限信息");
        }
        // 判断是否存在与角色的关联关系
        if (isPermissionAllowed(id)) {
            throw new IllegalArgumentException("该权限已被角色关联，不能删除");
        }
        // 删除权限信息
        super.removeById(id);
        // 删除与角色的关联关系
        rolePermissionMappingMapper.deleteRolePermissionMappingByPermissionId(id);
    }

    @Override
    public void updatePermission(PermissionDTO dto) {
        Long id = dto.getId();
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("权限id不能为空");
        }
        PermissionEntity permissionEntity = super.getById(id);
        if (Objects.isNull(permissionEntity)) {
            throw new IllegalArgumentException("不存在id为: " + id + "的权限信息");
        }
        dto.setApiUrl(permissionEntity.getApiUrl());
        BeanUtil.copyProperties(dto, permissionEntity);
        super.updateById(permissionEntity);
    }

    @Override
    public List<TotalPermissionVO> getPermissionList() {
        List<PermissionEntity> permissionEntities = super.list();
        List<TotalPermissionVO> permissionVOList = BeanUtil.copyToList(permissionEntities, TotalPermissionVO.class);
        for (TotalPermissionVO permissionVO : permissionVOList) {
            permissionVO.setUsed(isPermissionAllowed(permissionVO.getId()));
        }
        return permissionVOList;
    }

    /**
     * 判断权限是否正在被角色使用
     * @param permissionId 权限id
     */
    private boolean isPermissionAllowed(Long permissionId) {
        List<RolePermissionMappingEntity> mappingList = rolePermissionMappingMapper.getRolePermissionMappingByPermissionId(permissionId);
        if (!mappingList.isEmpty()) {
            long count = mappingList.stream().filter(RolePermissionMappingEntity::getAllowed).count();
            return count > 0;
        }
        return false;
    }
}
