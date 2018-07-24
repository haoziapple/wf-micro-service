package com.haozi.integration.msg;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CyclicBarrier;

@Configuration
public class MessageConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConfig.class);

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter adapter, Receiver2 receiver2) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);


        container.addMessageListener(adapter, new PatternTopic("chat"));

        MessageListenerAdapter adapter2 = new MessageListenerAdapter(receiver2, "handleMessage");
        adapter2.afterPropertiesSet();
        container.addMessageListener(adapter2, new PatternTopic("chat"));
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver(CyclicBarrier barrier) {
        return new Receiver(barrier);
    }

    @Bean
    Receiver2 receiver2(CyclicBarrier barrier) {
        return new Receiver2(barrier);
    }

    @Bean
    CyclicBarrier cyclicBarrier() {
        return new CyclicBarrier(3);
    }
}
