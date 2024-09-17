package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.RolePermissionMappingDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.RolePermissionMappingEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.RolePermissionMappingMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.RolePermissionMappingService;
import com.curriculumdesign.drugtraceabilitysystem.vo.RolePermissionMappingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionMappingServiceImpl extends ServiceImpl<RolePermissionMappingMapper, RolePermissionMappingEntity> implements RolePermissionMappingService {

    @Autowired
    private RolePermissionMappingMapper mapper;

    @Override
    public void updateStatus(RolePermissionMappingDTO dto) {
        LambdaQueryWrapper<RolePermissionMappingEntity> wrapper = new LambdaQueryWrapper<>();
        RolePermissionMappingEntity entity = super.getById(dto.getId());
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException("没有找到对应的角色权限映射关系");
        }
        Boolean oldAllowed = entity.getAllowed();
        if (!oldAllowed.equals(dto.getAllowed())) {
            entity.setAllowed(dto.getAllowed());
            mapper.updateAllowedById(entity.getId(), dto.getAllowed());
        }
    }

    @Override
    public List<RolePermissionMappingVO> getRolePermissionMappingByRoleId(Long id) {
        List<RolePermissionMappingEntity> entities = mapper.getRolePermissionMappingByRoleId(id);
        return BeanUtil.copyToList(entities, RolePermissionMappingVO.class);
    }
}
