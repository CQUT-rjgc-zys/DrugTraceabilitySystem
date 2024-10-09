package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.ManufacturerDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.WarehouseDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.ManufacturerService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.ManufacturerVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.WarehouseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
@CrossOrigin
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @PostMapping("/add")
    public RequestResult<Void> addManufacturer(@RequestBody ManufacturerDTO dto) {
        manufacturerService.addManufacturer(dto);
        return RequestResult.success();
    }

    @GetMapping("/getAll")
    public RequestResult<List<ManufacturerVO>> getAll() {
        List<ManufacturerVO> results = manufacturerService.getAll();
        return RequestResult.success(results);
    }

    @PostMapping("/fuzzyQuery")
    public RequestResult<List<ManufacturerVO>> fuzzyQuery(@RequestBody ManufacturerDTO dto) {
        List<ManufacturerVO> result = manufacturerService.fuzzyQuery(dto);
        return RequestResult.success(result);
    }

    @PostMapping("/delete")
    public RequestResult<Void> deleteManufacturer(@RequestBody List<Integer> ids){
        manufacturerService.deleteManufacturer(ids);
        return RequestResult.success();
    }

    @PostMapping("/update")
    public RequestResult<Void> updateManufacturer(@RequestBody ManufacturerDTO dto) {
        manufacturerService.updateManufacturer(dto);
        return RequestResult.success();
    }
}
