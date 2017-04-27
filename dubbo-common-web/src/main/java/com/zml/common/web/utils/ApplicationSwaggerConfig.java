package com.zml.common.web.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.zml.web.controller.user"})
public class ApplicationSwaggerConfig {

	@Bean
	public Docket createRestApi() {
		ApiInfo apiInfo = new ApiInfoBuilder()
		.title("RESTful APIs")
		.description("API接口说明")
		.termsOfServiceUrl("http://www.baidu.com")
		.version("v1.0")
		.build();
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiInfo)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.zml.web.controller.user"))
        .paths(PathSelectors.any())
        .build();
		
		return docket;
	}
}
