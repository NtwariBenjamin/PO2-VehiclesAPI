package com.udacity.vehicles.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public  Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }
    public ApiInfo apiInfo(){
        return new ApiInfo(
                "PO2-VehiclesAPI",
                "API that Contains Information about Boogle Maps,Prices of vehicles and the vehicles",
                "1",
                "http://localhost/8080/cars",
                new Contact("Benjamin Ntwari","www.benjaminNtwari.rw","benjamin.ntwari10@gmail.com"),
                "New License",
                "PO2-VehiclesAPI License", Collections.emptyList()

        );
    }
}
