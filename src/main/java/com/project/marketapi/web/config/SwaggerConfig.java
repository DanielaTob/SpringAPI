package com.project.marketapi.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //Le indicamos que esta clase se habilitará swagger2
public class SwaggerConfig {

    //Metodo que retorna un docket
    @Bean
    public Docket api(){
        //Aqui especificamos el tipon de documentacion a usar
        return new Docket(DocumentationType.SWAGGER_2)
                .select()  //Aqui indicamos los que queremos que exporte o exponga en la documentacion
                .apis(RequestHandlerSelectors.basePackage("com.project.marketapi.web.controller"))  //Solamente los que esten en controller y copiamos el paquete - quitar ; punto y coma
                .build();
    }
}
