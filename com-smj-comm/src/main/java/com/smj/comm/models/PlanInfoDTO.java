package com.smj.comm.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanInfoDTO implements Serializable {
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
