package com.yzm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Auther: yzm
 * @Date: 2021/6/17 - 06 - 17 - 19:20
 */

@Configuration
public class RememberMeConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    protected PersistentTokenRepository getPersistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //jdbcTokenRepository.setCreateTableOnStartup(true);//第一次启动时创建表，第二次启动时注释掉
        jdbcTokenRepository.setDataSource(dataSource);

        return jdbcTokenRepository;
    }
}
