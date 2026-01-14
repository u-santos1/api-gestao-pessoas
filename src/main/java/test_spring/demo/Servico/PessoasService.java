package test_spring.demo.Servico;


import org.springframework.stereotype.Service;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.DTO.PessoasResponseDTO;
import test_spring.demo.DTO.RequestCategoriaDTO;
import test_spring.demo.DTO.ResponseCategoriaDTO;
import test_spring.demo.DTO.infra.RecursoNaoEncotradoException;
import test_spring.demo.mapper.PessoaMap;
import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;
import test_spring.demo.repository.CategoriaRepository;
import test_spring.demo.repository.PessoaRepository;
import org.springframework.transaction.annotation.Transactional;



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
    public ResponseCategoriaDTO criarCategoria(RequestCategoriaDTO data){
        Pessoas pessoas = repository.findById(data.PessoaId()).orElseThrow(() -> new RecursoNaoEncotradoException("Pessoa nao encontrada"));
        Categoria categoria = new Categoria();
        categoria.setNome(data.nome());

        categoriaRepository.save(categoria);
        pessoas.setCategoria(categoria);
        repository.save(pessoas);

        return ResponseCategoriaDTO.fromEntity(categoria);

    }

    @Transactional
    public PessoasResponseDTO salvar(PessoasRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(()-> new RecursoNaoEncotradoException("categoria nao encontrada"));

        Pessoas entity = pessoaMap.toEntity(dto);
        entity.setCategoria(categoria);
        Pessoas salva = repository.save(entity);
        return pessoaMap.toDTO(salva);
    }
    @Transactional(readOnly = true)
    public List<PessoasResponseDTO> listarTodos(){
        List<Pessoas> pessoasBanco = repository.findAll();

        return pessoasBanco.stream().
                map(pessoaMap::toDTO)
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