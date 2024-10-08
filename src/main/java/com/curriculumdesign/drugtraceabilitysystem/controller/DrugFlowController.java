package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.DrugFlowDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugFlowService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugFlow")
@CrossOrigin
public class DrugFlowController {

    @Autowired
    private DrugFlowService drugFlowService;

    @PostMapping("/addDrugFlow")
    public RequestResult<String> addDrugFlow(@RequestBody DrugFlowDTO drugFlowDTO) {
        drugFlowService.addDrugFlow(drugFlowDTO);
        return RequestResult.success();
    }

    @GetMapping("/getDrugFlowsByDrugId/{drugId}")
    public RequestResult<List<DrugFlowDTO>> getDrugFlowsByDrugId(@PathVariable Integer drugId) {
        List<DrugFlowDTO> drugFlows = drugFlowService.getDrugFlowsByDrugId(drugId);
        return RequestResult.success(drugFlows);
    }
}
