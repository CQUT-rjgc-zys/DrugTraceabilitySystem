package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.RoleDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.PermissionEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.RoleEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.RolePermissionMappingEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.PermissionMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.RoleMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.RolePermissionMappingMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.UserMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.PermissionService;
import com.curriculumdesign.drugtraceabilitysystem.service.RolePermissionMappingService;
import com.curriculumdesign.drugtraceabilitysystem.service.RoleService;
import com.curriculumdesign.drugtraceabilitysystem.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMappingMapper rolePermissionMappingMapper;

    @Autowired
    private RolePermissionMappingService rolePermissionMappingService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<RoleVO> getRoles() {
        List<RoleEntity> roleEntities = super.list();
        return BeanUtil.copyToList(roleEntities, RoleVO.class);
    }

    @Override
    public RoleVO addRole(RoleDTO dto) {
        RoleEntity saveEntity = BeanUtil.copyProperties(dto, RoleEntity.class);
        super.save(saveEntity);
        // 保存角色与权限的关联关系
        List<PermissionEntity> permissionEntities = permissionMapper.selectList(null);
        List<RolePermissionMappingEntity> mappingEntities = new ArrayList<>();
        for (PermissionEntity permissionEntity : permissionEntities) {
            RolePermissionMappingEntity mappingEntity = new RolePermissionMappingEntity();
            mappingEntity
                    .setRoleId(saveEntity.getId())
                    .setPermissionId(permissionEntity.getId())
                    .setAllowed(false);
            mappingEntities.add(mappingEntity);
        }
        rolePermissionMappingService.saveBatch(mappingEntities);
        return BeanUtil.copyProperties(saveEntity, RoleVO.class);
    }

    @Override
    public boolean checkRoleCodeIsExist(String code) {
        LambdaQueryWrapper<RoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleEntity::getRoleCode, code);
        return super.count(queryWrapper) > 0;
    }

    @Override
    public void deleteRoleById(Long id) {
        RoleEntity roleEntity = super.getById(id);
        if (Objects.isNull(roleEntity)) {
            throw new IllegalArgumentException("不存在id：" + id + "对应的角色信息");
        }
        // 查询该角色是否有关联的权限，如果有，则不能删除
        int count = userMapper.getUserCountByRoleId(id);
        if (count > 0) {
            throw new IllegalArgumentException("存在当前角色用户，不能删除");
        }
        // 先删除角色与权限的关联关系
        rolePermissionMappingMapper.deleteByRoleId(id);
        // 再删除角色信息
        super.removeById(id);
    }

    @Override
    public void updateRole(RoleDTO dto) {
        Long id = dto.getId();
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("更新角色信息时，id不能为空");
        }
        RoleEntity roleEntity = super.getById(id);
        if (Objects.isNull(roleEntity)) {
            throw new IllegalArgumentException("不存在id：" + id + "对应的角色信息");
        }
        roleEntity.setRoleDesc(dto.getRoleDesc());
        super.updateById(roleEntity);
    }

    @Override
    public RoleVO getRoleById(Long id) {
        RoleEntity roleEntity = super.getById(id);
        return BeanUtil.copyProperties(roleEntity, RoleVO.class);
    }

    @Override
    public List<RoleVO> fuzzyQueryRoleByCode(String code) {
        LambdaQueryWrapper<RoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(RoleEntity::getRoleCode, code);
        List<RoleEntity> roleEntities = super.list(queryWrapper);
        return BeanUtil.copyToList(roleEntities, RoleVO.class);
    }

    @Override
    public List<RoleVO> fuzzyQueryRoleByName(String name) {
        LambdaQueryWrapper<RoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(RoleEntity::getRoleName, name);
        List<RoleEntity> roleEntities = super.list(queryWrapper);
        return BeanUtil.copyToList(roleEntities, RoleVO.class);
    }
}
