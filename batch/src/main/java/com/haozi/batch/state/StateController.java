package com.haozi.batch.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {
    @Autowired
    StateMachine<StateMachineConfig.States, StateMachineConfig.Events> stateMachine;

    @GetMapping("/state")
    public String state() throws InterruptedException {
        stateMachine.start();
        stateMachine.sendEvent(StateMachineConfig.Events.EVENT1);
        Thread.sleep(2000L);
        stateMachine.sendEvent(StateMachineConfig.Events.EVENT2);
        stateMachine.stop();
        return stateMachine.getState().toString();
    }
}
