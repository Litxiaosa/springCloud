package com.cloud.util.base;


import com.cloud.util.domanin.SessionUser;
import com.cloud.util.utils.Constants;
import com.cloud.util.utils.HtmlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;


/**
 * @author xiaosa
 */
public class BaseWebController {


    @Autowired
    private HttpServletRequest request;

    protected SessionUser getSessionUser() {
        return (SessionUser) request.getSession().getAttribute(Constants.USER);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
		//String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : HtmlFilter.filter(text.trim()));
            }
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
    }
}
