package com.okd.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.xml.txw2.Document;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Configuration
@Configuration
//Enable Swagger
@EnableSwagger2
public class SwaggerConfig {

	//Bean - Docket
	@Bean
	public Docket api() {
		// return new Docket(DocumentationType.SWAGGER_2);
		
		return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.okd.restfulwebservices"))
        .build();
	}
	//Swagger 2
	//All the paths
	//All the APis
	
	
}
