package com.haozi.purespring;

import com.haozi.purespring.simpleclazz.FooA;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 尝试使用beanFactory手动注册bean
 *
 * @author user
 */
@Component
public class BeanGenerator {
    private DefaultListableBeanFactory beanFactory;

    public BeanGenerator(DefaultListableBeanFactory beanFactory) throws ClassNotFoundException {
        this.beanFactory = beanFactory;

        FooA fooA = new FooA();
        fooA.setA("Hello ");
        fooA.setB("Wang Hao!");
        beanFactory.registerSingleton("fooA", fooA);

        // 仍然使用fooA的名称注册，会把上面的覆盖掉
        // TODO: 奇怪的是名字不用fooA的话，autowired的时候不会报错（相当于注册了两个FooA类，autowired的时候还是取第一个）
        // TODO: 怎么保证BeanGenerator在BeanUser之前先注册?（要不然fooA还没有创建，会报错）==> 使用@Order或@Priority注解
        // TODO: 好像是根据ClassName来的，Generator前面加个"A"就没问题。。,把BeanUser移动到子包里面也没问题
        beanFactory.registerBeanDefinition("fooA", BeanDefinitionBuilder
                .genericBeanDefinition(Class.forName("com.haozi.purespring.simpleclazz.FooA"))
                .addPropertyValue("a", "Hello ")
                .addPropertyValue("b", "World!")
                .setScope(ConfigurableBeanFactory.SCOPE_SINGLETON)
                .getRawBeanDefinition());
    }
}
