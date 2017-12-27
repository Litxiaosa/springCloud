package com.cloud.front.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaosa
 */
@RestController
@RequestMapping("/front")
@Api("IndexController相关Api")
public class IndexController {

    /**
     * 测试异常
     * @return
     * @throws Exception
     */
    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }
}
