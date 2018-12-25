package com.smj.comm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralSummary implements Serializable {

    private String SummaryName;

    private String SummaryCode;

    private String SummaryDescription;

    private String ReviewAdvice;

    private Boolean IsPrivacy;

    private String SummaryMedicalExplanation;

    private String SummaryReasonResult;

    private String SummaryAdvice;

    private Boolean F_Invisible;
}
