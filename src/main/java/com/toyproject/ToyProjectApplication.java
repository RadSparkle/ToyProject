package com.toyproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ToyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyProjectApplication.class, args);
    }

}
