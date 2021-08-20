package br.com.caulox.luizalabscommunicationapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Comunicação")
                        .version("v1.0.0")
                        .description("API de agendamento de mensagens. " +
                                "Utilizada para solicitar, consultar e cancelar o envio de mensagens dos tipos: " +
                                "Email, SMS, Push e WhatsApp")
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact()
                                .name("Carlos Eduardo Lourenço")
                                .url("https://www.linkedin.com/in/carlos-eduardo-lourenco/")
                        ));
    }
}
