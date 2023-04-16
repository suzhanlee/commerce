package com.commerce.web.global.config.swagger;

import static com.commerce.web.global.config.swagger.SwaggerConstant.DESCRIPTION;
import static com.commerce.web.global.config.swagger.SwaggerConstant.ITEM_GROUP;
import static com.commerce.web.global.config.swagger.SwaggerConstant.ITEM_GROUP_PATH;
import static com.commerce.web.global.config.swagger.SwaggerConstant.MEMBER_GROUP;
import static com.commerce.web.global.config.swagger.SwaggerConstant.MEMBER_GROUP_PATH;
import static com.commerce.web.global.config.swagger.SwaggerConstant.TITLE;
import static com.commerce.web.global.config.swagger.SwaggerConstant.VERSION;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi group1() {
        return GroupedOpenApi.builder()
                .group(ITEM_GROUP)
                .pathsToMatch(ITEM_GROUP_PATH)
                .build();
    }

    @Bean
    public GroupedOpenApi group2() {
        return GroupedOpenApi.builder()
                .group(MEMBER_GROUP)
                .pathsToMatch(MEMBER_GROUP_PATH)
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(getInfo());
    }

    private static Info getInfo() {
        return new Info()
                .version(VERSION)
                .description(DESCRIPTION)
                .title(TITLE);
    }

}
