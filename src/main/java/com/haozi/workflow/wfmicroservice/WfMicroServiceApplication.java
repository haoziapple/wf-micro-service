package com.haozi.workflow.wfmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WfMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WfMicroServiceApplication.class, args);
	}
}
