package com.smj.comm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckItem implements Serializable {

    public String CheckItemName;

    public String CheckItemCode;

    public String DepartmentName;

    public String SalePrice;

    public Integer CheckStateID;

    public String CheckUserName;

    public List<CheckResult> CheckResults;
}
