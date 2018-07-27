package com.haozi.mq.mq;

import com.haozi.mq.mq.kafka.ManualConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * 参考：https://docs.spring.io/spring-kafka/docs/2.1.2.RELEASE/reference/html/_introduction.html#quick-tour
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaQuickTests {
    @Autowired
    private ManualConfig.Listener listener;
    @Autowired
    private KafkaTemplate<Integer, String> template;


    @Test
    public void testSimple() throws Exception {
        template.send("annotated1", 0, "foo");
        template.flush();
        assertTrue(this.listener.getLatch1().await(10, TimeUnit.SECONDS));
    }
}
