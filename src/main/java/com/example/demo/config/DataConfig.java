//package com.example.demo.config;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.zaxxer.hikari.HikariDataSource;
//
//import javax.sql.DataSource;
//import java.sql.DriverManager;
//
//@Configuration
//public class DataConfig {
//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder.create()
//                .type(HikariDataSource.class)
//                .url("jdbc:mysql://localhost:3306/mydatabase")
//                .username("username")
//                .password("password")
//                .build();
//    }
//}
