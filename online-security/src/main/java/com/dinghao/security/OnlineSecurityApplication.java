package com.dinghao.security;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"com.dinghao"}, exclude = { DataSourceAutoConfiguration.class })
@EnableTransactionManagement
public class OnlineSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSecurityApplication.class,args);
    }
}
