package com.curriculumdesign.drugtraceabilitysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("drug")
public class DrugEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("manufacturer_id")
    private Integer manufacturerId;

    @TableField("name")
    private String name;

    @TableField("batch_number")
    private Integer batchNumber;

    @TableField("production_date")
    private LocalDateTime productionDate;

    @TableField("expiry_date")
    private LocalDateTime expiryDate;

    @TableField("status")
    private Integer status;
}
