package com.xyz.wxpay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.xyz.wxpay.mapper")
@ComponentScan(basePackages = {"com.xyz"})
public class WxpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxpayApplication.class, args);
    }

}
