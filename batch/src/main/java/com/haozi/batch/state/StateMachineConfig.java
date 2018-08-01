package com.haozi.batch.state;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.EnumSet;

/**
 * StateMachine配置类
 * 参考：https://projects.spring.io/spring-statemachine/#quick-start
 * 参考：https://docs.spring.io/spring-statemachine/docs/current-SNAPSHOT/reference/htmlsingle/
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig {
    /**
     * 定义两个状态
     */
    static enum States {
        STATE1, STATE2
    }

    /**
     * 定义两个事件，事件触发STATE的改变
     * EVENT1: STATE1-->STATE2
     * EVENT2: STATE2-->STATE1
     */
    static enum Events {
        EVENT1, EVENT2
    }

    @Bean
    public StateMachine<States, Events> buildMachine(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId("myMachineId")
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(States.STATE1)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.STATE1).target(States.STATE2)
                .event(Events.EVENT1)
                .and()
                .withExternal()
                .source(States.STATE2).target(States.STATE1)
                .event(Events.EVENT2);

        return builder.build();
    }
}
