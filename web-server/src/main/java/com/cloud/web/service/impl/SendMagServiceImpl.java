package com.cloud.web.service.impl;

import com.cloud.util.utils.Constants;
import com.cloud.util.utils.sms.SMSTemplate;
import com.cloud.util.utils.sms.SMSType;
import com.cloud.util.utils.sms.SMSUtil;
import com.cloud.web.service.ISendMagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaosa
 */
@Service
@Transactional
public class SendMagServiceImpl implements ISendMagService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 发短信， String... values: 可变参数
     * @param mobile
     * @param temp
     */
    @Override
    public Boolean sendMsg(String mobile, SMSTemplate temp, String... values) {
        //可写自己的逻辑业务，比如把信息储存数据库等等

        //信息内容
        String content=temp.getContent();
        switch (temp.getType()){
            case 0:
                for (int i = 0; i <  values.length; i++) {
                    content=content.replaceFirst("-",values[i]);
                }
                break;
            default:
        }
        //调用发短息接口
        String result = SMSUtil.sendMsg(mobile, content, SMSType.EMAY);
        if (result.contains(Constants.SUCCESS)) {
            return true;
        }else {
            logger.error("亿美短信发送失败:{}",result);
            return false;
        }
    }
}
