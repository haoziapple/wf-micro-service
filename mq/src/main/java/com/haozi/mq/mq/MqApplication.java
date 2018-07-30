package com.haozi.mq.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MqApplication implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(MqApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<String, String> template;


    private final CountDownLatch latch = new CountDownLatch(3);

    @Override
    public void run(String... args) throws Exception {
        this.template.send("myTopic", "foo1");
        this.template.send("myTopic", "foo2");
        this.template.send("myTopic", "foo3");
        latch.await(60, TimeUnit.SECONDS);
        logger.info("Messages-All-received");
    }

    @KafkaListener(topics = "myTopic", groupId = "group1")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info(cr.toString());
        latch.countDown();
    }

    /**
     * groupId相同的listener只会有一个收到消息
     * groupId不同的listener都会收到消息
     * Kafka allocates the partitions based on the group.id property - distributing partitions across the group
     *
     * @param cr
     * @throws Exception
     */
    @KafkaListener(topics = "myTopic", groupId = "group2")
    public void listen2(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("listen2: " + cr.toString());
    }
}
