package com.curriculumdesign.drugtraceabilitysystem.controller;

import com.curriculumdesign.drugtraceabilitysystem.dto.DrugDTO;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugService;
import com.curriculumdesign.drugtraceabilitysystem.util.RequestResult;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drug")
@CrossOrigin
public class DrugController {

    @Autowired
    private DrugService drugService;

    /**
     * 添加药品信息
     */
    @PostMapping("/add")
    public RequestResult<DrugVO> addDrug(@RequestBody DrugDTO dto) {
        DrugVO result = drugService.addDrug(dto);
        return RequestResult.success(result);
    }

    /**
     * 删除药品信息
     */
    @PostMapping("/delete")
    public RequestResult<String> deleteDrug(@RequestBody List<Integer> ids) {
        drugService.deleteDrug(ids);
        return RequestResult.success("删除成功");
    }

    /**
     * 更新药品信息
     */
    @PostMapping("/update")
    public RequestResult<Void> updateDrug(@RequestBody DrugDTO dto) {
        drugService.updateDrug(dto);
        return RequestResult.success();
    }

    @PostMapping("/fuzzyQuery")
    public RequestResult<List<DrugVO>> fuzzyQueryDrugs(@RequestBody DrugDTO dto) {
        List<DrugVO> results = drugService.fuzzyQueryDrugs(dto);
        return RequestResult.success(results);
    }

    /**
     * 根据药品名称查询药品信息
     */
    @GetMapping("/queryByName")
    public RequestResult<DrugVO> queryDrugByName(@RequestParam("name") String name) {
        DrugVO result = drugService.queryDrugByName(name);
        return RequestResult.success(result);
    }

    /**
     * 根据药品ID查询药品信息
     */
    @GetMapping("/queryById")
    public RequestResult<DrugVO> queryDrugById(@RequestParam("id") Integer id) {
        DrugVO result = drugService.queryDrugById(id);
        return RequestResult.success(result);
    }

    /**
     * 根据入库状态查询药品信息
     */
    @GetMapping("/queryDrugsByStatus")
    public RequestResult<List<DrugVO>> queryDrugsByStatus(@RequestParam("status") Integer status) {
        List<DrugVO> results = drugService.queryDrugsByStatus(status);
        return RequestResult.success(results);
    }

    @GetMapping("/getAll")
    public RequestResult<List<DrugVO>> getAllDrugs() {
        List<DrugVO> results = drugService.getAllDrugs();
        return RequestResult.success(results);
    }
}
