package com.shisan.note.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shisan.note.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())//令牌校验，配置全局token参数，使请求的接口自动添加token值。
                .securityContexts(securityContexts())
                .apiInfo(getApiInfo());
    }

    public ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("xxx应用平台") // 文档标题
                .description("xxx应用平台文档描述") // 文档描述
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    //设置完成后进入SwaggerUI，右上角出现“Authorization”按钮，
    //输入令牌对于除上文所述的包含auth的接口结果都会带上token。
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        //通过PathSelectors.regex("^(?!auth).*$")，排除包含"auth"的接口不需要使用securitySchemes
        securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}