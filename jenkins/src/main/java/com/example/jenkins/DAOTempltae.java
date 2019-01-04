package com.example.jenkins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Configuration
public class DAOTempltae {

    @Autowired
    NamedParameterJdbcTemplate customJDBCTemplate;

    public <T> CompletionStage<T> save(RowMapper<T> rowMapper, String sql, Map<String, ?> paramMap) {
        return CompletableFuture.supplyAsync(() -> customJDBCTemplate.queryForObject(sql, paramMap, rowMapper));
    }
}
