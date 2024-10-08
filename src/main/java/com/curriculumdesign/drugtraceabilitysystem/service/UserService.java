package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.UserDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.UserEntity;
import com.curriculumdesign.drugtraceabilitysystem.vo.UserVO;

public interface UserService extends IService<UserEntity> {

    void register(UserDTO user);

    void login(UserDTO user);

    UserVO getUserInfoByUsername(String username);
}
