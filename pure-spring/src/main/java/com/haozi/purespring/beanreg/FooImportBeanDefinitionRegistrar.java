package com.haozi.purespring.beanreg;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 使用ImportBeanDefinitionRegistrar手动注册bean
 * 参考：https://blog.csdn.net/lilongjiu/article/details/80405440
 * @author user
 */
public class FooImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = null;
        try {
            builder = BeanDefinitionBuilder
                    .genericBeanDefinition(Class.forName("com.haozi.purespring.simpleclazz.FooB"))
            .addPropertyValue("name", "my definition name");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        BeanDefinition beanDefinition = builder.getBeanDefinition();

        registry.registerBeanDefinition("fooB",beanDefinition);
    }
}
