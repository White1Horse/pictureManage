package com.azxx.picture.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket config() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.azxx.picture.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("图片管理系统-api文档").description("图片管理系统-api文档").termsOfServiceUrl("").version("1.0.0").build();
    }
}
