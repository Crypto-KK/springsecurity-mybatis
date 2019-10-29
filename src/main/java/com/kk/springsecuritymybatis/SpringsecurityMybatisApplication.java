package com.kk.springsecuritymybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kk.springsecuritymybatis.mapper")
public class SpringsecurityMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityMybatisApplication.class, args);
    }

}
