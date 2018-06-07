package com.haozi.workflow.wfmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableTransactionManagement
public class WfMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WfMicroServiceApplication.class, args);
    }

    /**
     * Modeler的Rest服务
     *
     * @return
     * 參考博客：
     * http://www.kafeitu.me/activiti/2015/12/27/integrate-new-activiti-modeler-and-rest.html
     * 暂时不使用独立的DispatchServlet，统一到ActivitiConfig当中
     */
//    @Bean
    public ServletRegistrationBean modelRestServlet() {
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext
                = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("org.activiti.rest.editor", "org.activiti.rest.diagram");
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet rest_dispatcherServlet
                = new DispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean
                = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/service/*");
        //指定name，如果不指定默认为dispatcherServlet
        registrationBean.setName("ModelRestServlet");
        return registrationBean;
    }

    /**
     * Rest接口
     *
     * @return
     * 參考博客：
     * http://www.kafeitu.me/activiti/2015/12/27/integrate-new-activiti-modeler-and-rest.html
     * 暂时不使用独立的DispatchServlet，统一到ActivitiConfig当中
     */
//    @Bean
    public ServletRegistrationBean restServlet() {
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext
                = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("org.activiti.rest");
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet rest_dispatcherServlet
                = new DispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean
                = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/rest/*");
        //指定name，如果不指定默认为dispatcherServlet
        registrationBean.setName("RestServlet");
        return registrationBean;
    }
}
