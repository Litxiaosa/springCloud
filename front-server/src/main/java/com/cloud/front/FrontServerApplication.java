package com.cloud.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiaosa
 * 主要逻辑：前台页面展示
 * EnableSwagger2:集成Swagger2，自动生成文档。
 * EnableFeignClients：声明式服务调用客户端，调用其他微服务
 */
@EnableSwagger2

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FrontServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontServerApplication.class, args);
	}
}
