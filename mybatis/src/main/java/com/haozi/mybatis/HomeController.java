package com.haozi.mybatis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/test")
    public String test(){
        return "success";
    }
}
