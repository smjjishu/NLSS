package com.smj.comm.enums;

public enum PlanTypeEnum {
    FIRST_PLAN(1, "一次计划"),
    SECOND_PLAN(2, "二次计划"),
    SUPPLY_PLAN(3, "补发计划"),
    OPERATE_PLAN(4, "运营短信"),;
    private int code;
    private String msg;

    PlanTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
