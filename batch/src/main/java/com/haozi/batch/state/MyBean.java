package com.haozi.batch.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.EventHeaders;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.util.Map;

@WithStateMachine(name = "buildMachine")
public class MyBean {
    public static final Logger logger = LoggerFactory.getLogger(MyBean.class);

    @OnTransition(target = "STATE1")
    public void toState1() {
        logger.info("===STATE1===MyBean changes to STATE1！");
    }

    @OnTransition(target = "STATE2")
    public void toState2() {
        logger.info("===STATE2===MyBean changes to STATE2！");
    }

    @OnTransition
    public void anyTransition(
            @EventHeaders Map<String, Object> headers,
            ExtendedState extendedState,
            StateMachine<String, String> stateMachine,
            Message<String> message,
            Exception e) {
        logger.info("===anyTransition===" + headers.toString());
        logger.info("===anyTransition===" + extendedState.toString());
        logger.info("===anyTransition===" + stateMachine.toString());
        logger.info("===anyTransition===" + (message == null ? "null" : message.toString()));
        logger.info("===anyTransition===" + (e == null ? "null" : e.getMessage()));
    }

    @OnTransition
    public void anyTransition2(StateContext<String, String> stateContext) {
        logger.info("===anyTransition2===" + stateContext.toString());
    }
}
