package com.haozi.mq.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class ListenerConfig {

    private String topic;

    public static Logger logger = LoggerFactory.getLogger(ListenerConfig.class);

    public ListenerConfig(@Value("${topic:myTopic}") String topic) {
        this.topic = topic;
    }

    /**
     * __listener指当前bean，也可以是spring中注册的beanName
     * @param cr
     * @throws Exception
     */
    @KafkaListener(topics = "#{__listener.topic}",
            groupId = "#{__listener.topic}.group")
    public void listen2(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("listen3: " + cr.toString());
    }


    public String getTopic() {
        return this.topic;
    }
}
