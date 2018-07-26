package com.haozi.batch.state;

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
    @OnTransition(target = "STATE1")
    public void toState1() {
        System.out.println("MyBean changes to STATE1！");
    }

    @OnTransition(target = "STATE2")
    public void toState2() {
        System.out.println("MyBean changes to STATE2！");
    }

    @OnTransition
    public void anyTransition(
            @EventHeaders Map<String, Object> headers,
            ExtendedState extendedState,
            StateMachine<String, String> stateMachine,
            Message<String> message,
            Exception e) {
    }

    @OnTransition
    public void anyTransition2(StateContext<String, String> stateContext) {
    }
}
