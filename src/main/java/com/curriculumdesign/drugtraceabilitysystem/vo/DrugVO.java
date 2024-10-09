package com.curriculumdesign.drugtraceabilitysystem.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DrugVO {

    private Integer id;

    private ManufacturerVO manufacturer;

    private WarehouseVO warehouse;

    private String name;

    private Integer batchNumber;

    private String productionDate;

    private String expiryDate;

    private String status;
}
