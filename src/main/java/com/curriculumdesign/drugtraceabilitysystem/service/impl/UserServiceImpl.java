package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.UserDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.UserEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.UserMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper baseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserDTO user) {
        UserEntity userEntity = BeanUtil.copyProperties(user, UserEntity.class);
        baseMapper.insert(userEntity);
    }
}
