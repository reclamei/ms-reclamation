package br.com.reclamei.company.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Reclamation API")
                .description("Service responsible for manage reclamation")
                .contact(new Contact()
                    .name("Canal do Cidadão"))
                .version("0.0.1"));
    }

}
