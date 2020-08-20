package com.mood.eshop.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan("com.mood.eshop.inventory.mapper")
public class EshopInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopInventoryApplication.class, args);
    }

}
