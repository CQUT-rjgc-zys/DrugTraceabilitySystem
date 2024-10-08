package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.UserDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.UserEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.UserMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.UserService;
import com.curriculumdesign.drugtraceabilitysystem.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public void register(UserDTO user) {
        UserEntity userEntity = BeanUtil.copyProperties(user, UserEntity.class);
        super.save(userEntity);
    }

    @Override
    public void login(UserDTO user) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, user.getUsername());
        queryWrapper.eq(UserEntity::getPassword, user.getPassword());
        UserEntity userEntity = super.getOne(queryWrapper);
        if (userEntity == null) {
            throw new RuntimeException("用户名或密码错误");
        }
    }

    @Override
    public UserVO getUserInfoByUsername(String username) {
        UserEntity userEntity = super.getOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUsername, username));
        Integer roleId = userEntity.getRoleId();
        return BeanUtil.copyProperties(userEntity, UserVO.class);
    }
}
