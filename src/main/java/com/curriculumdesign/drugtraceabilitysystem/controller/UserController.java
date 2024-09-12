package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.UserDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.UserService;
import com.curriculumdesign.drugtraceabilitysystem.util.common.RequestResult;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RequestResult<Void> register(@Validated @RequestBody UserDTO user) {
        userService.register(user);
        return RequestResult.success();
    }

    @GetMapping("/login1")
    public RequestResult<Void> login1() {
        return RequestResult.success();
    }

    @GetMapping("/login2")
    public String login2() {
        return "login";
    }
}
