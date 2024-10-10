package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.DrugFlowDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugFlowService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/drugFlow")
@CrossOrigin
public class DrugFlowController {

    @Autowired
    private DrugFlowService drugFlowService;

    @PostMapping("/addDrugFlow")
    public RequestResult<Void> addDrugFlow(@RequestBody List<DrugFlowDTO> dtos) {
        drugFlowService.addDrugFlow(dtos);
        return RequestResult.success();
    }

    @GetMapping("/getDrugFlowsByDrugId/{drugId}")
    public RequestResult<List<DrugFlowVO>> getDrugFlowsByDrugId(@PathVariable Integer drugId) {
        List<DrugFlowVO> drugFlows = drugFlowService.getDrugFlowsByDrugId(drugId);
        return RequestResult.success(drugFlows);
    }

    @GetMapping("/excel")
    public RequestResult<Void> exportExcel(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
        drugFlowService.exportExcel(id, response);
        return RequestResult.success();
    }

}
