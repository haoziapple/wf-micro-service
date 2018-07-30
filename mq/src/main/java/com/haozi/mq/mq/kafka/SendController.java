package com.haozi.mq.mq.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("send")
public class SendController {

    public static final Logger LOGGER = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    /**
     * 异步处理消息发送结果
     * @param topic
     * @param msg
     */
    @GetMapping("async")
    public void async(String topic, String msg) {
        final ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
        ListenableFuture<SendResult<String, String>> future = template.send(record);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("send msg async success: " + msg);
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.info("send msg async fail: " + msg);
                LOGGER.error(ex.getMessage(), ex);
            }
        });
    }

    /**
     * 同步处理消息发送结果
     * @param topic
     * @param msg
     */
    @GetMapping("sync")
    public void sync(String topic, String msg) {
        final ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);

        try {
            template.send(record).get(10, TimeUnit.SECONDS);
            LOGGER.info("send msg sync success: " + msg);
        } catch (Exception e) {
            LOGGER.info("send msg sync fail: " + msg);
            LOGGER.error(e.getMessage(), e);
        }

    }
}
