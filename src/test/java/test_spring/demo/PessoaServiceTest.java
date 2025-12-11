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
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.Servico.PessoasService;
import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;
import test_spring.demo.repository.CategoriaRepository;
import test_spring.demo.repository.PessoaRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
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

        PessoasRequestDTO requestDTO = new PessoasRequestDTO();
        requestDTO.setCategoriaId(1L);
        requestDTO.setNome("wesley test");
        requestDTO.setEmail("wesleytest@email.com");

        Pessoas pessoas = new Pessoas();
        pessoas.setId(1L);
        pessoas.setNome("wesley test");
        pessoas.setEmail("wesleytest@email.com");

        Mockito.when(categoriaRepository.findById(1L)).thenReturn(Optional.of(new Categoria()));
        Mockito.when(repository.save(any(Pessoas.class))).thenReturn(pessoas);

        Pessoas resultado = service.salvar(requestDTO);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("wesley test", resultado.getNome());
        Assertions.assertEquals("wesleytest@email.com", resultado.getEmail());
        Mockito.verify(repository, Mockito.times(1)).save(any(Pessoas.class));
    }
}