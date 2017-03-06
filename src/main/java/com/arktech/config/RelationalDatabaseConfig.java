package com.arktech.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by aruroyc on 06/03/17.
 */
@Configuration
public class RelationalDatabaseConfig {

    @Value("${database.connection.minPoolSize}")
    int minPoolSize=10;

    @Value("${database.connection.maxPoolSize}")
    int maxPoolSize=50;

    @Value("${database.url}")
    String JDBC_URL="evaluation-dev-db.ceoocvapzitb.ap-south-1.rds.amazonaws.com:3309";

    @Bean
    public DataSource createDataSource() throws Exception {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setMinPoolSize(minPoolSize);
        ds.setMaxPoolSize(maxPoolSize);
        ds.setJdbcUrl(JDBC_URL);
        ds.setDriverClass("com.mysql.jdbc.Driver");
        return ds;
    }
}
