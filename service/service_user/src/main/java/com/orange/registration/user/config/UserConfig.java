package com.orange.registration.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Orange
 * @create 2021-05-29 12:01
 */
@Configuration
@MapperScan("com.orange.registration.user.mapper")
public class UserConfig {
}
