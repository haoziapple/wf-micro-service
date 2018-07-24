package com.haozi.integration.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CyclicBarrier barrier;

    public Receiver(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void receiveMessage(String message) throws InterruptedException, BrokenBarrierException {
        LOGGER.info("Received <" + message + ">");
        // Receiver需要2秒钟处理信息
        Thread.sleep(2000L);
        barrier.await();
    }
}
