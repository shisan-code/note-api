package cn.shisan.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // 全局OpenAPI文档信息 + JWT认证配置
    @Bean
    public OpenAPI customOpenAPI() {
        // 定义安全方案：Bearer Token
        String securitySchemeName = "Authorization";
        SecurityScheme scheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT");

        return new OpenAPI()
                // 文档基础信息
                .info(new Info()
                        .title("后端接口文档")
                        .version("1.0.0")
                        .description("SpringBoot3.5 + SpringSecurity6 + MyBatis-Plus 前后端分离接口")
                        .contact(new Contact().name("开发团队"))

                )
                // 装载认证组件
                .components(new Components().addSecuritySchemes(securitySchemeName, scheme))
                // 全局所有接口默认携带Token
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}