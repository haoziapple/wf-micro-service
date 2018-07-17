package com.haozi.activiti6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/index")
    public Map<String, Object> index(){
        return Collections.singletonMap("message", "Hello");
    }
}
