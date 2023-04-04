package com.commerce.web.global.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

//    @Value("${spring.datasource.hikari.jdbc-url}")
//    private String jdbcUrl;
//    @Value("${spring.datasource.hikari.username}")
//    private String username;
//    @Value("${spring.datasource.hikari.password}")
//    private String password;
//
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(jdbcUrl);
//        config.setUsername(username);
//        config.setPassword(password);
//        return new HikariDataSource(config);
//    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:4306/study");
        config.setUsername("root");
        config.setPassword("root");
        return new HikariDataSource(config);
    }


}