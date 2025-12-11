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
import org.springframework.test.web.servlet.MockMvc;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.model.Categoria;
import test_spring.demo.repository.CategoriaRepository;

// Importações estáticas para facilitar a leitura
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DemoApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Test
    @DisplayName("Deve retornar 200 Created ao criar uma pessoa valida via API")
    void deveCriarPessoasViaApi() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria Test");
        categoria = categoriaRepository.save(categoria);

        PessoasRequestDTO requestDTO = new PessoasRequestDTO();
        requestDTO.setCategoriaId(categoria.getId());
        requestDTO.setNome("wesley");
        requestDTO.setEmail("wesley@email.com");


        mockMvc.perform(post("/api/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("wesley"));
    }
}