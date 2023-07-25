package com.huang.springboot3shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lenovo
 */
@MapperScan(basePackages = "com.huang.springboot3shopping.mapper")
@SpringBootApplication
public class SpringBoot3ShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3ShoppingApplication.class, args);
    }

}
