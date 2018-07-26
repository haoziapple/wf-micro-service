package com.haozi.batch;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.statemachine.support.StateMachineUtils;
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
        map.put("stateMachineVersion", StateMachineUtils.class.getPackage().getImplementationVersion());
        map.put("batchVersion", BatchProperties.class.getPackage().getImplementationVersion());
        return new ModelAndView(
                "home", map);
    }
}
