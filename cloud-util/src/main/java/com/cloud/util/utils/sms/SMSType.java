package com.cloud.util.utils.sms;

/**
 * @author xiaosa
 *
 */
public enum SMSType {

    EMAY("[亿美短信]");

    private String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    private SMSType(String type) {
        Type = type;
    }
}
