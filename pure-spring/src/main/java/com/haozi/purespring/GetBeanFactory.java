package com.haozi.purespring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GetBeanFactory {
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 使用构造器注入不需要Autowired注解
     * @param beanFactory
     * @param applicationContext
     */
    public GetBeanFactory(BeanFactory beanFactory, ApplicationContext applicationContext) {
        log.info("Get beanFactory: " + beanFactory.getClass().getName());
        log.info("Get applicationContext: " + applicationContext.getClass().getName());
        this.beanFactory = beanFactory;
        this.applicationContext = applicationContext;
    }

    // spring默认使用无参构造器
//    public GetBeanFactory() {
//        log.info("使用无参构造器");
//    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
