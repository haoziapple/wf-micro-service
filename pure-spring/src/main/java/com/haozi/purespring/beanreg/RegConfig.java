package com.haozi.purespring.beanreg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({FooImportBeanDefinitionRegistrar.class})
public class RegConfig {
}
