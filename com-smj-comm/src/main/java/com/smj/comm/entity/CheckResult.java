package com.smj.comm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckResult implements Serializable {

    private String CheckIndexName;

    private String CheckIndexCode;

    private String ResultValue;

    private String AppendInfo;

    private Boolean IsCalc;

    private String Unit;

    private String TextRef;

    private Boolean IsAbandon;

    private Integer ResultTypeID;

    private Integer ResultFlagID;

    private String LowValueRef;

    private String HighValueRef;

    private Integer ShowIndex;

    private String ValueRef;
}
