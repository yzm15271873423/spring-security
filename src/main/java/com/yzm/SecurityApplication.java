package com.yzm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Auther: yzm
 * @Date: 2021/6/16 - 06 - 16 - 16:03
 */
@MapperScan("com.yzm.mapper")
@SpringBootApplication
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityApplication {

    //这是main方法，是程序的入口
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }
}
