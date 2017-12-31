package com.cloud.util.utils.sms;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 亿美短信
 */
public class SMSEmay {

    private static Log log = LogFactory.getLog(SMSEmay.class);

    private static final String MSG_URL = "";
    private static final String CDKEY = "";
    private static final String KEY = "";
    private static final String PASSWORD = "";

    /**
     * 发短信
     *
     * @param phone
     * @param content
     * @return
     */
    public static String sendMsg(String phone, String content) {
        HttpClient httpClient = new HttpClient();
        try {
            PostMethod postMethod = new PostMethod(MSG_URL);
            // 在头文件中设置转码
            postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            NameValuePair[] data = {
                    new NameValuePair("cdkey", CDKEY),
                    new NameValuePair("password", PASSWORD),
                    new NameValuePair("phone", phone),
                    new NameValuePair("message", content)};
            postMethod.setRequestBody(data);
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                Header locationHeader = postMethod.getResponseHeader("location");
                String location = null;
                if (locationHeader != null) {
                    location = locationHeader.getValue();
                    log.info("The page was redirected to:" + location);
                } else {
                    log.info("Location field value is null.");
                }
            } else {
                String body = postMethod.getResponseBodyAsString();
                if (null != body && !"".equals(body)) {
                    log.info("短信结果为:" + body.trim());
                    body = xmlMt(body.trim());
                    return body;
                } else {
                    log.info("短信接口返回结果为空");
                    return null;
                }
            }
            postMethod.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 解析下发response
     */
    public static String xmlMt(String response) {
        String result = "0";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
            result = "-250";
        }
        Element root = document.getRootElement();
        result = root.elementText("error");
        if (null == result || "".equals(result)) {
            result = "-250";
        }
        return result;
    }
}