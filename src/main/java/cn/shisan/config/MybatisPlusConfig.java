package com.shisan.note.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = "com.shisan.note.mapper")
public class MybatisPlusConfig {



}