package com.cloud.web.service;

import com.cloud.util.utils.sms.SMSTemplate;

/**
 * @author xiaosa
 */
public interface IUserService {


    /**
     * 发短信, String... values: 可变参数
     * @param mobile
     * @param temp
     * @param values
     * @return
     */
    Boolean sendMsg(String mobile, SMSTemplate temp, String... values);
}
