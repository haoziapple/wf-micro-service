package com.haozi.mybatis;

import com.haozi.mybatis.generated.dao.FlywaySchemaHistoryMapper;
import com.haozi.mybatis.generated.entity.FlywaySchemaHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private FlywaySchemaHistoryMapper mapper;

    @GetMapping("/test")
    public FlywaySchemaHistory test(@RequestParam(defaultValue = "1") Integer id){
        return mapper.selectByPrimaryKey(id);
    }
}
