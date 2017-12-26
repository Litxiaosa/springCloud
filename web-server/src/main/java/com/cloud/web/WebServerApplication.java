package com.cloud.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiaosa
 * EnableSwagger2:集成Swagger2，自动生成文档。
 *EnableFeignClients：声明式服务调用客户端，调用其他微服务
 */
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.cloud.web.dao")
@SpringBootApplication
public class WebServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}
}
