package com.haozi.integration.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.EndpointId;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 参考：https://github.com/spring-projects/spring-integration-java-dsl/wiki/Spring-Integration-Java-DSL-Reference
 */

@Configuration
@EnableIntegration
public class MyConfiguration {

    @Bean
//    @EndpointId("input")
    @InboundChannelAdapter(channel = "inputChannel", poller = @Poller(fixedRate = "5000"))
    public MessageSource<?> integerMessageSource() {
        MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(new AtomicInteger());
        source.setMethodName("getAndIncrement");
        return source;
    }

    @Bean
    @InboundChannelAdapter(channel = "publishSubscribeChannel", poller = @Poller(fixedRate = "10000"))
    public MessageSource<?> StringMessageSource() {
        MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject("111111");
        source.setMethodName("toString");
        return source;
    }

    /**********************Channel Start**********************/
    @Bean
    // TODO:暂时不知道@EndpointId有什么用
//    @EndpointId("channel1")
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PublishSubscribeChannel publishSubscribeChannel() {
        return new PublishSubscribeChannel();
    }


    /**********************Channel End**********************/

    // TODO:不知道怎么引用下面这个定义好的poller
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(1000L).get();
    }


    /**********************Flow Start**********************/
    @Bean
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from(
//                "inputChannel")
                // 下面这个写法也可以，而且很神奇，integerMessageSource，inputChannel的方法都只调用一次，没有重复创建
                // 奇怪的是打印偶数的间隔变成5s了（设想应该是10s）
                // 懂了！这样写的话上面的InboundChannelAdapter注解仍然起作用，也就是说一个source上面注册了两个5秒间隔的poller
                this.integerMessageSource(), c ->
                c.poller(Pollers.fixedRate(5000L)))
                .channel(this.inputChannel())
                //
                .filter((Integer p) -> p % 2 == 0)
                .<Object, String>transform(Object::toString)
                .channel(MessageChannels.queue())
                .log()
                .get();
    }

    @Bean
    public IntegrationFlow integerFlow() {
        return IntegrationFlows.from(this.publishSubscribeChannel()) //这种写法跟下面相比更好些，可以防止硬编码
                .<String, Integer>transform(Integer::parseInt)
                //TODO：不懂为什么下面这种写法不行，因为Integer的toString方法有好几个？
//                .<Integer, String>transform(Integer::toString)
                .log()
                .get();

    }

    @Bean
    public IntegrationFlow stringFlow() {
        return IntegrationFlows.from("publishSubscribeChannel")
                .filter("111111"::equals)
                .transform("Hello "::concat)
                .handle(System.out::println)
                .get();
    }
}
