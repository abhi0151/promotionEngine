package com.promotionengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(new ApiInfoBuilder()
                        .title("Swagger Super")
                        .description("Swagger Description details")
                        .version("1.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.promotionengine"))
                .paths(PathSelectors.any()).build();
    }
}