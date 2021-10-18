package com.jdbcTemplate.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Demo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getallstudentnames(){
        List<String> names = new ArrayList<>();
        names.addAll(jdbcTemplate.queryForList("SELECT NAME FROM STUDENT",String.class));
        return names;

    }
}
