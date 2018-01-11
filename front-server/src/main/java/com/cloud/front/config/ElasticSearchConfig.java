package com.cloud.front.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 搜索引擎ES的config
 * @author xiaosa
 */
@Configuration
public class ElasticSearchConfig {

    @Bean
    public TransportClient client() throws UnknownHostException {
        ////tcp端口，默认9300
        //当时我们配置了三个节点，我们可以new 三个InetSocketTransportAddress，然后都addTransportAddress到里面
        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300);

        //初始化一个自定义的配置
        Settings settings = Settings.builder()
                //集群名字，当时我配置的叫xiaosa
                .put("cluster.name", "xiaosa").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return  client;
    }
}
