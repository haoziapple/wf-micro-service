package com.haozi.integration;

import groovy.lang.GroovySystem;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
    public static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/")
    public ModelAndView home() {
        this.template.send("myTopic", "Welcome Home!");
        Map<String, String> map = new HashMap<>();
        map.put("bootVersion", Banner.class.getPackage().getImplementationVersion());
        map.put("groovyVersion", GroovySystem.getVersion());

        return new ModelAndView(
                "index", map);
    }

    @KafkaListener(topics = "myTopic", groupId = "listen2")
    public void listen2(ConsumerRecord<?, ?> cr) throws Exception {
        LOGGER.info("listen2: " + cr.toString());
    }
}
