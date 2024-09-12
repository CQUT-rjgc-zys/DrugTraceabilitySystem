package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.UserDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.UserEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.UserMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.UserService;
import com.curriculumdesign.drugtraceabilitysystem.util.ModelTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper baseMapper;

    @Override
    public void register(UserDTO user) {
        UserEntity userEntity = ModelTransformation.transform(user, UserEntity.class);
        baseMapper.insert(userEntity);
    }
}
