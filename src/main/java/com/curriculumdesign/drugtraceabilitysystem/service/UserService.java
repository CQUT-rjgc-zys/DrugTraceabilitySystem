package com.curriculumdesign.drugtraceabilitysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curriculumdesign.drugtraceabilitysystem.dto.UserDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.UserEntity;

public interface UserService extends IService<UserEntity> {

    void register(UserDTO user);

}
