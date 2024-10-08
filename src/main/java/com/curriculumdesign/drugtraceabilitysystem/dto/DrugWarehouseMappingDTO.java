package com.curriculumdesign.drugtraceabilitysystem.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DrugWarehouseMappingDTO {

    private Integer id;

    private Integer drugId;

    private Integer warehouseId;

    private LocalDateTime entryDate;

    private LocalDateTime exitDate;
}
