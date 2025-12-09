package test_spring.demo.Servico;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.DTO.PessoasResponseDTO;
import test_spring.demo.DTO.infra.RecursoNaoEncotradoException;
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

    @Autowired
    PessoaRepository repository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public Pessoas salvar(PessoasRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RecursoNaoEncotradoException("Categoria nao encontrada!"));

        Pessoas pessoas = new Pessoas();
        pessoas.setNome(dto.getNome());
        pessoas.setEmail(dto.getEmail());

        pessoas.setCategoria(categoria);
        return repository.save(pessoas);
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

        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());
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