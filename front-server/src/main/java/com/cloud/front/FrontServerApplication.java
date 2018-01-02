package com.cloud.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiaosa
 * 主要逻辑：前台页面展示
 * EnableSwagger2:集成Swagger2，自动生成文档。
 * EnableFeignClients：声明式服务调用客户端，调用其他微服务
 * EnableScheduling: 启用定时任务
 */
@EnableSwagger2
@MapperScan("com.cloud.front.dao")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
@EnableHystrix
public class FrontServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontServerApplication.class, args);
	}
}
