package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.UserDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.UserService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RequestResult<Void> register(@RequestBody UserDTO user) {
        userService.register(user);
        return RequestResult.success();
    }

    @PostMapping("/login")
    public RequestResult<Void> login(@RequestBody UserDTO user) {
        userService.login(user);
        return RequestResult.success();
    }

    @PostMapping("/getUserInfoByUsername")
    public RequestResult<UserVO> getUserInfoByUsername(@RequestParam("username") String username) {
        UserVO user = userService.getUserInfoByUsername(username);
        return RequestResult.success(user);
    }
}
