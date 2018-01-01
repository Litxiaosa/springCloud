package com.cloud.web.amqp;

import com.cloud.util.utils.sms.SMSTemplate;
import com.cloud.web.service.ISendMagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 发短信
 * @author xiaosa
 */
@Component
public class NotifyMsgListener {

    private Logger logger = LoggerFactory.getLogger(NotifyMsgListener.class);

    @Autowired
    private ISendMagService sendMagService;

    @RabbitListener(queues = "queue.sendMsg")
    public void process(Map<String,Object> map){
        try {
            //发短信
            sendMagService.sendMsg((String)map.get("mobile"), SMSTemplate.SEND_MSG, (String)map.get("userName"));
        }catch (Exception e){
            logger.error("短信提醒业务处理异常{}",e);
        }
    }
}
