package test_spring.demo.model;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestao de Pessoas - Uerles",
                version = "1.0",
                description = "API desenvolvida para gestao de colaboradores e setores. Projeto de estudo de Arquitetura Backend.",
                contact = @Contact(
                        name = "Uerles",
                        email = "wlsantos1155@gmail.com"
                )
        )
)
public class OpenApiConfig {


}
