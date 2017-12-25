package com.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author xiaosa
 * 服务过滤
 * 通过Zuul Pre过滤器，将session的信息传递给微服务。
 * 否则user-service拿不到用户的session，会认为是匿名用户而拒绝访问。
 * 另外在GatewayApplication上如果加上注解
 * @EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)，意思是告诉Redis立即保存session。
 */
public class AccessFilter extends ZuulFilter{

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);


    /**
     * 前置过滤器,有四种类型
     * @return
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 优先级为0，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * 是否执行该过滤器，此处为true，说明需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * 过滤器的具体逻辑，这里将拦截器移动到了各个微服务的拦截器内。
     * @return
     */
    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpSession httpSession = ctx.getRequest().getSession();

        SessionUser sessionUser= httpSession.getAttribute(Constants.USER);
        if (sessionUser != null) {
            logger.debug("session中有用户信息:" + sessionUser.getMobile());
        } else {
            logger.debug("session中没有用户信息");
        }
        logger.debug("sessionID:"+httpSession.getId());

        //打印header
        Enumeration names = ctx.getRequest().getHeaderNames();
        logger.debug("header信息开始++++++++++++++++++++++");
        while (names.hasMoreElements()){
            String name = (String) names.nextElement();
            String value = ctx.getRequest().getHeader(name);
            System.out.println(name+":"+value);
        }
        logger.debug("header信息END++++++++++++++++++++++");
        //x-auth-token
        ctx.addZuulRequestHeader("Cookie", "SESSION=" + httpSession.getId());
        return null;
    }
}
