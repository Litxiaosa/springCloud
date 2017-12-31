package com.cloud.util.utils.sms;

/**
 * @author xiaosa
 */

public enum SMSTemplate {

    /**
     * 正确的格式：【短信签名】+短信正文；
     *【】必须使用中文格式，不要使用英文[]，通道不支持[]作为签名符号，[]会失败；
     * 短信签名】，原则上要求3~8个字中文，要求和营业执照公司名称关键字或相关业务有关。
     * 短信签名】一定要放在短信的最前面，同时避免在一条内容中出现多个签名，否则将会失败。
     * 签名不正确或者提交的短信签名和报备不一致将会发送失败。
     */
    SEND_MSG(0, "【潇洒提醒您】 感谢-荣幸的成为本人的测试用户！！大家鼓掌。");

    private int type;
    private String content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private SMSTemplate(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public static SMSTemplate fromType(int type) {
        if (type == 0){
            return SEND_MSG;
        }
        for (SMSTemplate temp : SMSTemplate.values()) {
            if (type == temp.type) {
                return temp;
            }
        }
        return SEND_MSG;
    }
}
