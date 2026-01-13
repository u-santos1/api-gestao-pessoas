package test_spring.demo.Servico;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.DTO.PessoasResponseDTO;
import test_spring.demo.DTO.infra.RecursoNaoEncotradoException;
import test_spring.demo.mapper.PessoaMap;
import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;
import test_spring.demo.repository.CategoriaRepository;
import test_spring.demo.repository.PessoaRepository;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
public class PessoasService {

    final private PessoaRepository repository;
    final private CategoriaRepository categoriaRepository;
    final private PessoaMap pessoaMap;

    public PessoasService(PessoaRepository repository, CategoriaRepository categoriaRepository, PessoaMap pessoaMap){
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
        this.pessoaMap = pessoaMap;
    }

    @Transactional
    public PessoasResponseDTO salvar(Long id, PessoasRequestDTO dto) {
        Pessoas pessoas = repository.findById(id).orElseThrow(() -> new RecursoNaoEncotradoException("Pessoa nao encontrada"));
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncotradoException("Categoria nao encontrada!"));

        pessoas.setNome(dto.nome());
        pessoas.setEmail(dto.email());
        pessoas.setCategoria(categoria);

        return pessoaMap.toDTO(pessoas);
    }
    public List<PessoasResponseDTO> listarTodos(){
        List<Pessoas> pessoasBanco = repository.findAll();

        return pessoasBanco.stream().
                map(pessoas -> new PessoasResponseDTO(pessoas))
                .toList();
    }
    public Pessoas atualizar(Long id, PessoasRequestDTO dto){
        Pessoas existente = repository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncotradoException("Pessoa nao encotrada"));
        Categoria novaCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncotradoException("Categoria nao encontrada"));

        existente.setNome(dto.nome());
        existente.setEmail(dto.email());
        existente.setCategoria(novaCategoria);
        return repository.save(existente);
    }
    public void deletar(Long id){
        if(!repository.existsById(id)) {
            throw new RecursoNaoEncotradoException("Pessoas nao encotrada com id");
        }
        repository.deleteById(id);
    }
}