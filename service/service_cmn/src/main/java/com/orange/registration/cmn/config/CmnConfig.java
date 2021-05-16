package com.orange.registration.cmn.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Orange
 * @create 2021-05-16 21:35
 */
@Configuration
@MapperScan("com.orange.registration.cmn.mapper")
public class CmnConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
