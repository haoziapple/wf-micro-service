package com.haozi.integration.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@RestController
public class MessageController {
    public static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private CyclicBarrier cyclicBarrier;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/send")
    public String send(String msg) throws InterruptedException, BrokenBarrierException {

        LOGGER.info("Sending message...");
        redisTemplate.convertAndSend("chat", msg);
        cyclicBarrier.await();
        LOGGER.info("Receiver finish receiving message...");

        return "success";
    }

}
