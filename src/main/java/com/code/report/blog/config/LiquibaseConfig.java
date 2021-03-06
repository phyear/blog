package com.code.report.blog.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zhaotianxin
 * @date 2021-01-19 15:30
 */
@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        // liquibase.setDefaultSchema(DEFAULT_SCHEMA);
        liquibase.setChangeLog("classpath:changelog.groovy");
        return liquibase;
    }

}
