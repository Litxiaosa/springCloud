package com.cloud.util.utils.sms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.cloud.util.utils.Constants.FAILURE;
import static com.cloud.util.utils.Constants.SUCCESS;

/**
 * @author xiaosa
 */
public class SMSUtil {

    /**
     * 判断是否为手机号
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        String mobileVerify = "\"^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\\\d{8}$\"";
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // 验证手机号
        p = Pattern.compile(mobileVerify);
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 发送短信
     * @param mobile
     * @param content
     * @param type
     * @return
     */
    public static String sendMsg(String mobile,String content, SMSType type) {
        String result = FAILURE;
        result = SMSEmay.sendMsg( mobile,content);
        if (result!=null && "0".equals(result)) {
            result = SUCCESS;
        }
        return result+type.getType();
    }

}
