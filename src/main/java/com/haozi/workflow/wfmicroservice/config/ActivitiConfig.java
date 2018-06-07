package com.haozi.workflow.wfmicroservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haozi.workflow.wfmicroservice.activiti.UsersFormType;
import org.activiti.engine.*;
import org.activiti.engine.form.AbstractFormType;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.rest.common.application.ContentTypeResolver;
import org.activiti.rest.common.application.DefaultContentTypeResolver;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * @Description activiti配置类
 * @date 2018-06-06 17:27
 */
@Configuration
@ComponentScan(basePackages = {"org.activiti.conf",
        "org.activiti.rest",
        "org.activiti.rest.editor",
        "org.activiti.rest.service",
        "org.activiti.rest.diagram"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})})
public class ActivitiConfig {
    /**
     * 单例json对象
     *
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ProcessEngineConfigurationImpl processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setJobExecutorActivate(true);
        // 自動部署
        processEngineConfiguration.setDeploymentResources(new Resource[]{new ClassPathResource("/deployments/dispatch.bar"),
                new ClassPathResource("/deployments/leave.bar"),
                new ClassPathResource("/deployments/leave-dynamic-from.bar"),
                new ClassPathResource("/deployments/leave-formkey.bar"),
                new ClassPathResource("/deployments/leave-jpa.zip"),
                new ClassPathResource("/deployments/timerExample.zip")});
        // 自定义表单字段类型
        List<AbstractFormType> customFormTypes = new ArrayList<>();
        customFormTypes.add(new UsersFormType());
        processEngineConfiguration.setCustomFormTypes(customFormTypes);
        return processEngineConfiguration;
    }

    @Bean(name = "processEngine")
    public ProcessEngineFactoryBean processEngine(ProcessEngineConfigurationImpl configuration) {
        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
        factoryBean.setProcessEngineConfiguration(configuration);
        return factoryBean;
    }

    //    七大接口
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    //    集成REST服务需要的bean
    @Bean
    public RestResponseFactory restResponseFactory() {
        return new RestResponseFactory();
    }

    @Bean
    public ContentTypeResolver contentTypeResolver() {
        return new DefaultContentTypeResolver();
    }

}
