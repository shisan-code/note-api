package cn.shisan.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = "cn.shisan.mapper")
public class MybatisPlusConfig {



}