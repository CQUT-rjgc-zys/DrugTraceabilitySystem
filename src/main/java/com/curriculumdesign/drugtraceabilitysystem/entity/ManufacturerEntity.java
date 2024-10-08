package com.curriculumdesign.drugtraceabilitysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("manufacturer")
public class ManufacturerEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("location")
    private String location;

    @TableField("license_number")
    private String licenseNumber;

    @TableField("phone")
    private String phone;
}
