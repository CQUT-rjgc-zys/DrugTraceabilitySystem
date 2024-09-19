package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.RoleDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.RolePermissionMappingDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.RolePermissionMappingService;
import com.curriculumdesign.drugtraceabilitysystem.service.RoleService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.RolePermissionMappingVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @Autowired
    private RolePermissionMappingService rolePermissionMappingService;

    /**
     * 添加角色信息
     *
     * @param dto 角色信息
     * @return {@link RoleVO} 角色信息（包含id）
     */
    @PostMapping("/addRole")
    public RequestResult<RoleVO> addRole(@RequestBody @Validated RoleDTO dto) {
        RoleVO roleVO = service.addRole(dto);
        return RequestResult.success(roleVO);
    }

    /**
     * 根据角色ID删除角色信息
     *
     * @param id 角色ID
     */
    @DeleteMapping("/deleteRoleById")
    public RequestResult<Void> deleteRoleById(@RequestParam("id") Long id) {
        service.deleteRoleById(id);
        return RequestResult.success();
    }

    /**
     * 更新角色信息（只能修改描述信息）
     *
     * @param dto 角色信息
     */
    @PostMapping("/updateRole")
    public RequestResult<Void> updateRole(@RequestBody @Validated RoleDTO dto) {
        service.updateRole(dto);
        return RequestResult.success();
    }

    /**
     * 禁用/启用权限
     *
     * @param dto 角色权限映射信息
     */
    @PostMapping("/updateStatus")
    public RequestResult<Void> updateStatus(@RequestBody @Validated RolePermissionMappingDTO dto) {
        rolePermissionMappingService.updateStatus(dto);
        return RequestResult.success();
    }

    /**
     * 根据角色id查询角色信息及相关权限信息
     *
     * @param id 角色ID
     * @return {@link RoleVO} 角色信息
     */
    @GetMapping("/getRoleById")
    public RequestResult<RoleVO> getRoleById(@RequestParam("id") Long id) {
        RoleVO result = service.getRoleById(id);
        return RequestResult.success(result);
    }

    /**
     * 根据角色代码获取角色信息（模糊查询）
     *
     * @param code 角色代码
     * @return {@link List<RoleVO>} 角色信息
     */
    @GetMapping("/fuzzyQueryRoleByCode")
    public RequestResult<List<RoleVO>> fuzzyQueryRoleByCode(@RequestParam("code") String code) {
        List<RoleVO> results = service.fuzzyQueryRoleByCode(code);
        return RequestResult.success(results);
    }

    /**
     * 根据角色名称获取角色信息（模糊查询）
     *
     * @param name 角色名称
     * @return {@link List<RoleVO>} 角色信息
     */
    @GetMapping("/fuzzyQueryRoleByName")
    public RequestResult<List<RoleVO>> fuzzyQueryRoleByName(@RequestParam("name") String name) {
        List<RoleVO> results = service.fuzzyQueryRoleByName(name);
        return RequestResult.success(results);
    }

    /**
     * 获取所有角色信息
     *
     * @return {@link List<RoleVO>} 角色信息列表
     */
    @GetMapping("/getRoles")
    public RequestResult<List<RoleVO>> getRoles() {
        return RequestResult.success(service.getRoles());
    }

    /**
     * 根据角色代码检查是否存在角色信息
     *
     * @param code 角色代码
     * @return true：存在；false：不存在
     */
    @GetMapping("/checkRoleCodeIsExist")
    public RequestResult<Boolean> checkRoleCodeIsExist(@RequestParam("code") String code) {
        boolean result = service.checkRoleCodeIsExist(code);
        return RequestResult.success(result);
    }

    /**
     * 查询角色信息关联的权限信息
     *
     * @param id 角色ID
     * @return {@link List<RolePermissionMappingDTO>} 角色权限映射信息列表
     */
    @GetMapping("/getRolePermissionMappingByRoleId")
    public RequestResult<List<RolePermissionMappingVO>> getRolePermissionMappingByRoleId(@RequestParam("id") Long id) {
        List<RolePermissionMappingVO> results = rolePermissionMappingService.getRolePermissionMappingByRoleId(id);
        return RequestResult.success(results);
    }
}
