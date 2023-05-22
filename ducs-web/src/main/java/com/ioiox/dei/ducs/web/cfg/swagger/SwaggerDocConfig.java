package com.ioiox.dei.ducs.web.cfg.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerDocConfig {

    @Bean
    public Docket ducCoreDocket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(ducCoreApiInfo()).enable(true)
                .select()
                //apis： 添加swagger接口提取范围
                .apis(RequestHandlerSelectors.basePackage("com.ioiox.dei.duc.springboot.rest.api"))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo ducCoreApiInfo() {
        return new ApiInfoBuilder()
                .title("蛋蛋岛用户中心核心接口")
                .description("蛋蛋岛用户中心核心接口")
                .contact(new Contact("山成", "", "cheng.shan@hotmail.com"))
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}
