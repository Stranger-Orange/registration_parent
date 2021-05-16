package com.orange.registration.hosp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Orange
 * @create 2021-05-16 21:35
 */
@Configuration
@MapperScan("com.orange.registration.hosp.mapper")
public class HospConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
