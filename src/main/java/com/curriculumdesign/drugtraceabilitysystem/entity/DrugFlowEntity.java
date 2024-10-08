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

    @TableField("upstream")
    private Integer upstream;

    @TableField("downstream")
    private Integer downstream;

    @TableField("time")
    private LocalDateTime time;
}
