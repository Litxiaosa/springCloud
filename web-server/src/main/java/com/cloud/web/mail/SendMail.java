package com.cloud.web.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Locale;
import java.util.Map;

/**
 * @author xiaosa
 */
@Configuration
public class SendMail{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${postMail}")
    private String postMail;

    /**
     * 发送邮件的模版
     * @throws MessagingException
     */
    public void sendMailTemplate(String mail, String subject, Map<String, Object> model) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //开启带附件true
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        IContext context = new Context(Locale.CHINESE, model);
        //获取模板html代码
        String process = templateEngine.process("mail", context);
        messageHelper.setFrom(postMail);
        messageHelper.setTo(mail);
        //主题
        messageHelper.setSubject(subject);
        messageHelper.setText(process, true);

        //附件
        //FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
        //messageHelper.addAttachment("附件-1.jpg", file);
        //messageHelper.addAttachment("附件-2.jpg", file);

        mailSender.send(mimeMessage);
    }
}
