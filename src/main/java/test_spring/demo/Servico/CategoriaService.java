package test_spring.demo.Servico;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_spring.demo.DTO.RequestCategoriaDTO;
import test_spring.demo.DTO.ResponseCategoriaDTO;
import test_spring.demo.DTO.infra.RecursoNaoEncotradoException;
import test_spring.demo.mapper.PessoaMap;
import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;
import test_spring.demo.repository.CategoriaRepository;
import test_spring.demo.repository.PessoaRepository;

import java.util.List;
@Slf4j
@Service
public class CategoriaService {

    final private CategoriaRepository categoriaRepository;
    final private PessoaRepository repository;
    public CategoriaService(PessoaRepository repository, CategoriaRepository categoriaRepository, PessoaMap pessoaMap){
        this.categoriaRepository = categoriaRepository;
        this.repository = repository;
    }
    @Transactional
    public ResponseCategoriaDTO criarCategoria(RequestCategoriaDTO data){
        Pessoas pessoas = repository.findById(data.pessoaId()).orElseThrow(() -> new RecursoNaoEncotradoException("Pessoa nao encontrada"));
        Categoria categoria = new Categoria();
        categoria.setNome(data.nome());

        categoriaRepository.save(categoria);
        pessoas.setCategoria(categoria);
        repository.save(pessoas);

        return ResponseCategoriaDTO.fromEntity(categoria);
    }
    public List<ResponseCategoriaDTO> listarTodos(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(ResponseCategoriaDTO::fromEntity)
                .toList();
    }
}
