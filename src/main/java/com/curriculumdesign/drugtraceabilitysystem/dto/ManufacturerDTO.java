package com.curriculumdesign.drugtraceabilitysystem.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class ManufacturerDTO {

    private Integer id;

    private String name;

    private String location;

    private String licenseNumber;

    private String phone;
}
