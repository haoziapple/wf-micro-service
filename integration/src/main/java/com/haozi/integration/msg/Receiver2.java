package com.haozi.integration.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 第二个Receiver
 */
public class Receiver2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver2.class);

    private CyclicBarrier barrier;

    public Receiver2(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void handleMessage(String message) throws InterruptedException, BrokenBarrierException {
        LOGGER.info("Receiver2 Received <" + message + ">");
        // Receiver2需要3秒钟处理信息
        Thread.sleep(3000L);
        barrier.await();
    }
}
