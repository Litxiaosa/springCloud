package com.cloud.web.config;

import com.cloud.util.domanin.SessionUser;
import com.cloud.util.utils.Constants;
import com.cloud.util.utils.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author xiaosa
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Enumeration<String> headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            Enumeration<String> values = request.getHeaders(name);
            while(values.hasMoreElements()){
                logger.info("header信息{}:{}",name,values.nextElement());
            }
        }

        logger.info("当前sessoinID：{}，当前请求URL:{},请求IP:{}", request.getSession().getId(),request.getRequestURI(),request.getRemoteHost());
        //获取session中的user
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(Constants.USER);
        if(sessionUser!=null){
            return  true;
        }
        //解决中文乱码
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().write(ErrorCode.USER_NOT_LOGIN.toString());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
