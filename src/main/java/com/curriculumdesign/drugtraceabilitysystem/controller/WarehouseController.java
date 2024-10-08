package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.DrugWarehouseMappingDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.WarehouseDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.WarehouseService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.WarehouseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/add")
    public RequestResult<WarehouseVO> addWarehouse(@RequestBody WarehouseDTO dto){
        WarehouseVO result = warehouseService.addWarehouse(dto);
        return RequestResult.success(result);
    }

    @PostMapping("/delete")
    public RequestResult<Void> deleteWarehouse(@RequestBody List<Integer> ids){
        warehouseService.deleteWarehouse(ids);
        return RequestResult.success();
    }

    @PostMapping("/update")
    public RequestResult<Void> updateWarehouse(@RequestBody WarehouseDTO dto){
        warehouseService.updateWarehouse(dto);
        return RequestResult.success();
    }

    @GetMapping("/queryById/{id}")
    public RequestResult<WarehouseVO> queryById(@PathVariable("id") Integer id) {
        WarehouseVO result = warehouseService.queryById(id);
        return RequestResult.success(result);
    }

    @GetMapping("/queryByName/{name}")
    public RequestResult<WarehouseVO> queryByName(@PathVariable("name") String name) {
        WarehouseVO result = warehouseService.queryByName(name);
        return RequestResult.success(result);
    }

    @PostMapping("/fuzzyQuery")
    public RequestResult<List<WarehouseVO>> fuzzyQuery(@RequestBody WarehouseDTO dto) {
        List<WarehouseVO> result = warehouseService.fuzzyQuery(dto);
        return RequestResult.success(result);
    }

    @GetMapping("/getAll")
    public RequestResult<List<WarehouseVO>> getAll() {
        List<WarehouseVO> results = warehouseService.getAll();
        return RequestResult.success(results);
    }

    /**
     * 新增药品入库信息
     */
    @GetMapping("/addDrugWarehouseMapping")
    public RequestResult<Void> addDrugWarehouseMapping(@RequestBody DrugWarehouseMappingDTO dto) {
        warehouseService.addDrugWarehouseMapping(dto);
        return RequestResult.success();
    }

    @PostMapping("/deleteDrugWarehouseMapping")
    public RequestResult<Void> deleteDrugWarehouseMapping(@RequestParam("id") Integer id) {
        warehouseService.deleteDrugWarehouseMapping(id);
        return RequestResult.success();
    }

    @PostMapping("/updateDrugWarehouseMapping")
    public RequestResult<Void> updateDrugWarehouseMapping(@RequestBody DrugWarehouseMappingDTO dto) {
        warehouseService.updateDrugWarehouseMapping(dto);
        return RequestResult.success();
    }



}
