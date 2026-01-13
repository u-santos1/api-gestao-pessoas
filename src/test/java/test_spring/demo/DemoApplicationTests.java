package test_spring.demo; // (ou test_spring.demo.controller)

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.model.Categoria;
import test_spring.demo.repository.CategoriaRepository;

// Importações estáticas para facilitar a leitura
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(properties = {
        // 1. URL (Removi o MODE=PostgreSQL para evitar confusão, H2 puro é mais seguro para testes simples)
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        // 2. A CORREÇÃO FINAL: Força o Hibernate a falar "H2 nativo"
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
public class DemoApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Test
    @DisplayName("Deve retornar 200 Created ao criar uma pessoa valida via API")
    void deveCriarPessoasViaApi() {}}