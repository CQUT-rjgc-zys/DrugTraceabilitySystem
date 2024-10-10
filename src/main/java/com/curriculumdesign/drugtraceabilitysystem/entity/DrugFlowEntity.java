package com.curriculumdesign.drugtraceabilitysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("drug_flow")
public class DrugFlowEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("drug_id")
    private Integer drugId;

    @TableField("distributor_id")
    private Integer distributorId;

    @TableField("index")
    private Integer index;

    @TableField("time")
    private LocalDateTime time;
}
