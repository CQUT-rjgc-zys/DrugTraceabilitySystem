package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.UserDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.UserService;
import com.curriculumdesign.drugtraceabilitysystem.util.common.RequestResult;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RequestResult<Void> register(@RequestBody @Validated UserDTO user) {
        userService.register(user);
        return RequestResult.success();
    }
}
