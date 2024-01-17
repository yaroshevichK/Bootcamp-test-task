package it.bootcamp.conf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI myOpenApi() {
        Contact contact = new Contact()
                .name("Katerina Yaroshevich");

        Info info = new Info()
                .contact(contact)
                .title("Test task IT Bootcamp System API");

        return new OpenAPI()
                .info(info);
    }
}
