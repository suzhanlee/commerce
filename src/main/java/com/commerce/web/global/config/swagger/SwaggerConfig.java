package com.commerce.web.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi group1() {

        return GroupedOpenApi
            .builder()
            .group("item")
            .pathsToMatch("/api/item/*")
            .build();

    }

    @Bean
    public GroupedOpenApi group2() {

        return GroupedOpenApi
            .builder()
            .group("member")
            .pathsToMatch("/api/member/*")
            .build();

    }


    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI().info(getInfo());

    }



    private static Info getInfo() {
        return new Info()
            .version("1.0.0")
            .description("COMMERCE REST API DOC")
            .title("COMMERCE");
    }



}
