package com.cloud.web.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaosa
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue("queue.sendMsg");
    }
}
