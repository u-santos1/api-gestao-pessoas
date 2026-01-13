package test_spring.demo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoTestRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.Servico.PessoasService;
import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;
import test_spring.demo.repository.CategoriaRepository;
import test_spring.demo.repository.PessoaRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
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
public class PessoaServiceTest {

    @Mock
    private PessoaRepository repository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private PessoasService service;

    @Test
    @DisplayName("Deve salvar Pessoas")
    void deveSalvarPessoas(){


    }
}