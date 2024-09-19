package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.PermissionDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.PermissionService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.PermissionVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.TotalPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 根据权限ID删除权限
     *
     * @param id 权限id
     */
    @PostMapping("/deletePermission")
    public RequestResult<Void> deletePermissionById(Long id) {
        service.deletePermissionById(id);
        return RequestResult.success();
    }

    /**
     * 根据权限ID更新权限信息
     *
     * @param dto 权限信息
     */
    @PostMapping("/updatePermission")
    public RequestResult<Void> updatePermission(@RequestBody @Validated PermissionDTO dto) {
        service.updatePermission(dto);
        return RequestResult.success();
    }

    /**
     * 获取权限列表
     * @return {@link List<TotalPermissionVO>} 权限列表
     */
    @PostMapping("/getPermissionList")
    public RequestResult<List<TotalPermissionVO>> getPermissionList() {
        List<TotalPermissionVO> results = service.getPermissionList();
        return RequestResult.success(results);
    }
}
