package com.haozi.purespring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author user
 */
@Component
public class GetContext {
    private ApplicationContext applicationContext;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * set方法注入必须使用Autowired注解
     * @param applicationContext
     */
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        //AnnotationConfigServletWebServerApplicationContext
        log.info("Get application context: " + applicationContext.getClass().getName());
        this.applicationContext = applicationContext;
    }
}
