package com.curriculumdesign.drugtraceabilitysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("drug_warehouse_mapping")
public class DrugWarehouseMappingEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("drug_id")
    private Integer drugId;

    @TableField("warehouse_id")
    private Integer warehouseId;

    @TableField("entry_date")
    private LocalDateTime entryDate;

    @TableField("exit_date")
    private LocalDateTime exitDate;
}
