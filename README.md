# SpringCloud

### 一个简单的SpringCloud框架Demo

- eureka-server: 注册中心，端口号：7001
- config-server：配置中心，端口号：7002
- web-gateway：服务网关， 端口号：8001
- web-server：普通的微服务，可写业务逻辑，比如注册登录等等。端口号：8002
- front-server：普通的微服务，可写业务逻辑，比如前台的页面展示等等。端口号：8003
- cloud-util：本项目的工具类

### 本项目包含

- 使用 `Swagger2` 构建的RESTful API文档。(如果只是写接口可使用)
- 微服务的统一异常处理
- Thymeleaf
- 集成MyBatis
- front-server 集成redis
- web-server 集成邮件发送，需要在config-server 配置你的授权码
- web-server 集成亿美短信提醒，需要你在cloud-util的SMSEmay类里面修改亿美的配置
- front-server 增加定时任务

