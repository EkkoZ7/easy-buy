package com.ekko.easy.buy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.ekko.easy.buy.mapper")
public class EasyBuyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyBuyApplication.class, args);
    }

}
