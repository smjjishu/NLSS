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
public class GeneralAdvice implements Serializable {

    private Integer AdviceCode;

    private String AdviceName;

    private String AdviceDescription;

    private String IsPrivacy;

    private int ShowIndex;

    private String GeneralSummarys;

    private Boolean F_Invisible;
}
