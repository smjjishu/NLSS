package com.smj.comm.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 计划表
 */
@TableName("ccs_plan")
@ApiModel(value = "计划表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PlanEntity implements Serializable {

    @ApiModelProperty(value = "主键Id")
    private String id;

    @ApiModelProperty(value = "机构编号")
    private String unitCode;

    @ApiModelProperty(value = "机构名称")
    private String unitName;

    @ApiModelProperty(value = "分类")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}