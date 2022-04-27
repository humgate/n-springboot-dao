package com.humga.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Repository
public class AppRepository {
    private final String QUERY_RESOURCE_FILE = "select.sql";
    private String query;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    AppRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @PostConstruct
    public void readQueryFromResourceFile() throws IOException {
        File resource = new ClassPathResource(QUERY_RESOURCE_FILE).getFile();
        query = new String(Files.readAllBytes(resource.toPath()));
    }

    public List<String> getProduct(String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        return namedParameterJdbcTemplate.queryForList(query,parameters,String.class);
    }
}
