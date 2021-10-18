package com.jdbcTemplate.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    Demo demo;

    @GetMapping(value = "/studentname")
    public String studentName(){
        return "studentname";
    }

    @GetMapping(value = "/studentnames")
    public List<String> studentnames(){

        return demo.getallstudentnames();
    }

}
