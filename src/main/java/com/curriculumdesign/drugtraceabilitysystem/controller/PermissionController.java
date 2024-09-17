package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.PermissionDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.PermissionService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService service;

    /**
     * 新增权限
     *
     * @param dto 权限信息
     * @return {@link PermissionVO} 权限信息
     */
    @PostMapping("/addPermission")
    public RequestResult<PermissionVO> addPermission(@RequestBody @Validated PermissionDTO dto) {
        PermissionVO result = service.addPermission(dto);
        return RequestResult.success(result);
    }
}
