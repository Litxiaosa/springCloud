# SpringCloud

### 一个简单的SpringCloud框架Demo

- eureka-server: 注册中心，端口号：7001
- config-server：配置中心，端口号：7002
- web-gateway：服务网关， 端口号：8001
- web-server：普通的微服务，可写业务逻辑，比如注册登录等等。端口号：8002
- front-server：普通的微服务，可写业务逻辑，比如前台的页面展示等等。端口号：8003
- cloud-util：本项目的工具类

### 本项目包含

- 健康检查
- 声明式服务调用客户端
- 服务容错保护(Hystrix服务降级、Hystrix依赖隔离、Hystrix断路器)。需要在具体执行逻辑的函数上增加@HystrixCommand注解来指定服务降级方法
  例如：
    
    ```
    @HystrixCommand(fallbackMethod = "fallback")
    public String consumer() {
        return restTemplate.getForObject("http://web-server/index", String.class);
    }
    
    public String fallback() {
        return "fallback";
    }
    ```
    
- 使用 `Swagger2` 构建的RESTful API文档。(如果只是写接口可使用)
- 微服务的统一异常处理
- Thymeleaf
- 集成MyBatis
- front-server 集成redis
- web-server 集成邮件发送，需要在config-server 配置你的授权码
- web-server 集成亿美短信提醒，需要你在cloud-util的SMSEmay类里面修改亿美的配置
- front-server 增加定时任务
- 彩蛋：Spring Boot自定义Banner
  在Spring Boot工程的 `/src/main/resources` 目录下创建一个`banner.txt`文件,然后将ASCII字符画复制进去，就能替换默认的banner了。
  还使用了一些属性设置
  `${AnsiColor.BRIGHT_RED}`：设置控制台中输出内容的颜色
  `${application.version}`：用来获取MANIFEST.MF文件中的版本号
  `${application.formatted-version}`：格式化后的
  `${application.version}`版本信息
  `${spring-boot.version}`：Spring Boot的版本号
  `${spring-boot.formatted-version}`：格式化后的`${spring-boot.version}`版本信息
  
  比如下图：
  ![](http://litxiaosa.oss-cn-shanghai.aliyuncs.com/hexo/85B7E8D6-C4D3-474B-9CBB-801954CD3F42.png)
  
  ```
  




