package com.orange.registration.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Orange
 * @create 2021-05-29 11:51
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.orange")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.orange")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
