package test_spring.demo.Servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.DTO.PessoasResponseDTO;
import test_spring.demo.DTO.infra.RecursoNaoEncotradoException;

import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;

import test_spring.demo.repository.CategoriaRepository;
import test_spring.demo.repository.PessoaRepository;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class PessoasService {

    final private PessoaRepository repository;
    final private CategoriaRepository categoriaRepository;


    public PessoasService(PessoaRepository repository, CategoriaRepository categoriaRepository){
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;

    }
    @Transactional
    public PessoasResponseDTO salvar(PessoasRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(()-> new RecursoNaoEncotradoException("categoria nao encontrada"));

        Pessoas entity = new Pessoas();
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setCategoria(categoria);
        Pessoas salva = repository.save(entity);
        return PessoasResponseDTO.toDTO(salva);
    }
    @Transactional(readOnly = true)
    public Page<PessoasResponseDTO> listarTodos(Pageable paginacao){
        return repository.findAll(paginacao)
                .map(PessoasResponseDTO::toDTO);
    }
    public PessoasResponseDTO atualizar(Long id, PessoasRequestDTO dto){
        Pessoas existente = repository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncotradoException("Pessoa nao encotrada"));
        Categoria novaCategoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncotradoException("Categoria nao encontrada"));

        existente.setNome(dto.nome());
        existente.setEmail(dto.email());
        existente.setCategoria(novaCategoria);
        Pessoas salvar = repository.save(existente);
        return PessoasResponseDTO.toDTO(salvar);
    }
    public void deletar(Long id){
        if(!repository.existsById(id)) {
            throw new RecursoNaoEncotradoException("Pessoas nao encotrada com id");
        }
        repository.deleteById(id);
    }@Transactional(readOnly = true)
    public Page<PessoasResponseDTO> buscarPorCategoria(String nomeCategoria, Pageable pageable){
        Page<Pessoas> paginaDePessoas = repository.findByCategoriaNome(nomeCategoria, pageable);
        return paginaDePessoas.map(PessoasResponseDTO::toDTO);
    }
}