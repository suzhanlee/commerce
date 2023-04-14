package com.commerce.web.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi getItemApi() { // groupOpenApi를 통해 API 를 묶음 처리하여 표시 가능

        return GroupedOpenApi
            .builder()
            .group("item")
            .pathsToMatch("/api/item/**")
            .build();

    }

    @Bean
    public GroupedOpenApi getMemberApi() {

        return GroupedOpenApi
            .builder()
            .group("member")
            .pathsToMatch("/api/member/**")
            .build();

    }

    //위 그룹을 응용을 하게 되면 .packagesToScan("kr.co.gizmo80.test.order")를 이용해서
    // 컨트롤러 별로 그룹도 가능 하고, 버전별로 나눌수도 있을거 같다.

    //To enable the support of multiple OpenAPI definitions, a bean of type GroupedOpenApi needs to be defined.

    @Bean
    public OpenAPI getOpenApi() {

        return new OpenAPI().components(new Components())
            .info(getInfo()); // .info() : 해당 OPENAPI 에 대한 정보 설정

    }


    private Info getInfo() {
        return new Info()
            .version("1.0.0")
            .description("COMMERCE REST API DOC")
            .title("COMMERCE");
    }


}
