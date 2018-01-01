package com.cloud.util.utils.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaosa
 */
public class MailUtil {

    /**
     * 判断是否为邮件
     * @param str
     * @return
     */
    public static boolean isMail(String str) {
        String mailVerify = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+" +
                "[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // 验证邮箱
        p = Pattern.compile(mailVerify);
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
