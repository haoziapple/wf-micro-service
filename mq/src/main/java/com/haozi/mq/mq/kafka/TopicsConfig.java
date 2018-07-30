package com.haozi.mq.mq.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * 配置Kafka的topics
 */
@Configuration
public class TopicsConfig implements InitializingBean {
    @Autowired
    private KafkaAdmin admin;

    public static final Logger LOGGER = LoggerFactory.getLogger(TopicsConfig.class);

    // 创建topic1
    @Bean
    public NewTopic topic1() {
        // TODO:replicationFactor参数是干什么用的？为什么设置成2创建topic失败，设置成1就可以
        return new NewTopic("topic1", 10, (short) 1);
    }

    // 创建topic2
    @Bean
    public NewTopic topic2() {
        return new NewTopic("topic2", 10, (short) 1);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Get kafka config: "+admin.getConfig().toString());
    }
}
