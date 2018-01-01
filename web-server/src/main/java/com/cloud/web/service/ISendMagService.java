package com.cloud.web.service;

import com.cloud.util.utils.sms.SMSTemplate;

public interface ISendMagService {

    /**
     * 发短信, String... values: 可变参数
     * @param mobile
     * @param temp
     * @param values
     * @return
     */
    Boolean sendMsg(String mobile, SMSTemplate temp, String... values);
}
