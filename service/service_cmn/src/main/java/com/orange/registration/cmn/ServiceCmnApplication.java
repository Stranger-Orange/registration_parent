package com.orange.registration.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Orange
 * @create 2021-05-16 22:28
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.orange")
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }
}
