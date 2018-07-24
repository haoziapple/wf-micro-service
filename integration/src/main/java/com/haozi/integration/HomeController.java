package com.haozi.integration;

import groovy.lang.GroovySystem;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView home() {
        Map<String, String> map = new HashMap<>();
        map.put("bootVersion", Banner.class.getPackage().getImplementationVersion());
        map.put("groovyVersion", GroovySystem.getVersion());

        return new ModelAndView(
                "index", map);
    }
}
