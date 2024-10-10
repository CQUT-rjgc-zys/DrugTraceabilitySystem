package com.curriculumdesign.drugtraceabilitysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugDTO;
import com.curriculumdesign.drugtraceabilitysystem.dto.DrugFlowDTO;
import com.curriculumdesign.drugtraceabilitysystem.entity.DistributorEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.DrugFlowEntity;
import com.curriculumdesign.drugtraceabilitysystem.entity.ManufacturerEntity;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DistributorMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DrugFlowMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DrugMapper;
import com.curriculumdesign.drugtraceabilitysystem.mapper.DrugWarehouseMappingMapper;
import com.curriculumdesign.drugtraceabilitysystem.service.DistributorService;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugFlowService;
import com.curriculumdesign.drugtraceabilitysystem.service.DrugService;
import com.curriculumdesign.drugtraceabilitysystem.service.ManufacturerService;
import com.curriculumdesign.drugtraceabilitysystem.vo.DistributorVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugFlowVO;
import com.curriculumdesign.drugtraceabilitysystem.vo.DrugVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class DrugFlowServiceImpl extends ServiceImpl<DrugFlowMapper, DrugFlowEntity> implements DrugFlowService {

    @Autowired
    private DrugService drugService;

    @Autowired
    private DistributorService distributorService;

    @Autowired
    private DistributorMapper distributorMapper;

    @Autowired
    private ManufacturerService manufacturerService;

    @Override
    public List<DrugFlowVO> getDrugFlowsByDrugId(Integer drugId) {
        LambdaQueryWrapper<DrugFlowEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DrugFlowEntity::getDrugId, drugId);
        queryWrapper.orderByAsc(DrugFlowEntity::getIndex);
        List<DrugFlowEntity> drugFlowEntities = this.list(queryWrapper);
        List<DrugFlowVO> drugFlowVOS = new ArrayList<>();
        for (DrugFlowEntity drugFlowEntity : drugFlowEntities) {
            DrugFlowVO drugFlowVO = BeanUtil.copyProperties(drugFlowEntity, DrugFlowVO.class);
            Integer distributorId = drugFlowEntity.getDistributorId();
            DistributorEntity distributorEntity = distributorService.getById(distributorId);
            drugFlowVO.setDistributor(BeanUtil.copyProperties(distributorEntity, DistributorVO.class));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日");
            drugFlowVO.setTime(drugFlowEntity.getTime().format(formatter));
        }
        return drugFlowVOS;
    }

    @Override
    public void addDrugFlow(List<DrugFlowDTO> dtos) {
        DrugFlowDTO drugFlowDTO = dtos.get(0);
        Integer drugId = drugFlowDTO.getDrugId();
        distributorMapper.deleteByDrugId(drugId);
        List<DrugFlowEntity> entities = new ArrayList<>();
        int index = 1;
        for (DrugFlowDTO dto : dtos) {
            DrugFlowEntity drugFlowEntity = BeanUtil.copyProperties(dto, DrugFlowEntity.class);
            drugFlowEntity.setIndex(index++);
            entities.add(drugFlowEntity);
        }
        super.saveBatch(entities);
    }

    @Override
    public void exportExcel(Integer id, HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("药品信息流向");

        DrugEntity drugEntity = drugService.getById(id);
        Integer manufacturerId = drugEntity.getManufacturerId();
        ManufacturerEntity manufacturerEntity = manufacturerService.getById(manufacturerId);
        int index = 0;
        Row row = sheet.createRow(index++);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("名称");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("地址");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("联系电话");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("编号");

        row = sheet.createRow(index++);
        cell0 = row.createCell(0);
        cell0.setCellValue(manufacturerEntity.getName());
        cell1 = row.createCell(1);
        cell1.setCellValue(manufacturerEntity.getLocation());
        cell2 = row.createCell(2);
        cell2.setCellValue(manufacturerEntity.getPhone());
        cell3 = row.createCell(3);
        cell3.setCellValue(manufacturerEntity.getLicenseNumber());

        List<DrugFlowVO> drugFlowsByDrugId = getDrugFlowsByDrugId(id);
        for (DrugFlowVO drugFlowVO : drugFlowsByDrugId) {
            cell0 = row.createCell(0);
            cell0.setCellValue(drugFlowVO.getDistributor().getName());
            cell1 = row.createCell(1);
            cell1.setCellValue(drugFlowVO.getDistributor().getLocation());
            cell2 = row.createCell(2);
            cell2.setCellValue(drugFlowVO.getDistributor().getPhone());
            cell3 = row.createCell(3);
            cell3.setCellValue(drugFlowVO.getDistributor().getLicenseNumber());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ExcelFile.xlsx");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
