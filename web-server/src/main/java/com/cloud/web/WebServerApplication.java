package com.cloud.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiaosa
 *
 * 主要业务：登录注册 （根据个人需要）
 * EnableSwagger2:集成Swagger2，自动生成文档。
 */
@EnableSwagger2
@EnableDiscoveryClient
@MapperScan("com.cloud.web.dao")
@SpringBootApplication
@EnableHystrix
public class WebServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}
}
