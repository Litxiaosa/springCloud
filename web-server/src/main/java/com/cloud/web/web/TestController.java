package com.cloud.web.web;

import com.cloud.util.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Api注解: 表示标识这个类是swagger的资源
 */
@RestController
@RequestMapping("/web")
@Api("webController相关Api")
public class TestController {

    /**
     * 测试异常
     * @return
     * @throws Exception
     */
    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    /**
     * 测试Swagger2
     * @param request
     * @return
     * @ApiOperation()用于方法，表示一个http请求的操作，value:表示这个接口的作用，notes：对着接口的一些说明
     * hidden: 表示隐藏这个接口
     */
    @ApiOperation(value = "注销接口", notes = "不用传参数,登陆就行", hidden = true)
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.USER);
        request.getSession().invalidate();
        return "success";
    }
}
