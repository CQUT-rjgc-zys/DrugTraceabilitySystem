package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.DistributorDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.ManufacturerDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.DistributorService;
import com.curriculumdesign.drugtraceabilitysystem.service.ManufacturerService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.DistributorVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.ManufacturerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distributor")
@CrossOrigin
public class DistributorController {

    @Autowired
    private DistributorService distributorService;

    @PostMapping("/add")
    public RequestResult<Void> addDistributor(@RequestBody DistributorDTO dto) {
        distributorService.addDistributor(dto);
        return RequestResult.success();
    }

    @GetMapping("/getAll")
    public RequestResult<List<DistributorVO>> getAll() {
        List<DistributorVO> results = distributorService.getAll();
        return RequestResult.success(results);
    }

    @PostMapping("/fuzzyQuery")
    public RequestResult<List<DistributorVO>> fuzzyQuery(@RequestBody DistributorDTO dto) {
        List<DistributorVO> result = distributorService.fuzzyQuery(dto);
        return RequestResult.success(result);
    }

    @PostMapping("/delete")
    public RequestResult<Void> deleteDistributor(@RequestBody List<Integer> ids){
        distributorService.deleteDistributor(ids);
        return RequestResult.success();
    }
}
