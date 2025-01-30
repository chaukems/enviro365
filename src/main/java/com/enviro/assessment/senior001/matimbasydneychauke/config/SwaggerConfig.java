package com.enviro.assessment.senior001.matimbasydneychauke.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {


    @Bean
    public OpenAPI reportsServiceApi() {

        Contact contact = new Contact();
        contact.setEmail("info@enviro365.com");
        contact.setName("Enviro365");
        contact.setUrl("http://www.enviro365.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Reports Service API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage reports.")
                .termsOfService("http://www.enviro365.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}