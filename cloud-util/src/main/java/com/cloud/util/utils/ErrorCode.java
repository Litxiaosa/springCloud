package com.cloud.util.utils;

public enum ErrorCode {

    SYSTEM_BUSY("10001", "system is busy now !"),


    USER_NOT_LOGIN("20014", "用户未登陆,请登陆后重试!");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ErrorCode fromCode(String code) {
        if (code == null) {
            return SYSTEM_BUSY;
        }
        for (ErrorCode status : ErrorCode.values()) {
            if (code.equals(status.code)) {
                return status;
            }
        }
        return SYSTEM_BUSY;
    }
}
