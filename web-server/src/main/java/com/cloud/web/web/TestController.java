package com.cloud.web.web;

import com.cloud.web.mail.SendMail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Api注解: 表示标识这个类是swagger的资源
 * @author xiaosa
 */
@RestController
@RequestMapping("/test")
@Api("webController相关Api")
public class TestController {

    @Autowired
    private SendMail sendMail;

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Value("${postMail}")
    private String postMail;


    /**
     * 测试统一异常处理
     * 访问 localhost:8001/web/error
     * @return
     * @throws Exception
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String error() throws Exception {
        throw new Exception("发生错误！！");
    }


    @PostMapping("sendMail")
    @ApiOperation(value = "")
    public void sendMail(){
        String mail = "litxiaosa@qq.com";
        String subject = "SpringBoot Thymeleaf模板邮件";
        Map<String, Object> model = new HashMap<>(1);
        model.put("userName", "潇洒");
        try {
            sendMail.sendMailTemplate(mail, subject, model);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 发短息
     */
    @PostMapping("sendSMS")
    @ApiOperation(value = "")
    public void sendSMS(String mobile, String userName){
        Map map = new HashMap<String, Object>(2);
        map.put("mobile", mobile);
        map.put("userName", userName);
        //发短信
        rabbitTemplate.convertAndSend("queue.sendMsg", map);
    }

}
