package com.haozi.activiti6;

import org.springframework.stereotype.Component;

@Component
public class ResumeService {

    public void storeResume() {
        System.out.println("=====Activiti is rendering spring bean=====");
        System.out.println("Storing resume ...");
    }

}