package test_spring.demo.controller;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.hibernate.metamodel.model.domain.ListPersistentAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.DTO.PessoasResponseDTO;
import test_spring.demo.Servico.PessoasService;
import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;
import test_spring.demo.repository.CategoriaRepository;
import test_spring.demo.repository.PessoaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class CategoriaCadastro {

    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    PessoasService service;

    @PostMapping("/categoria")
    public Categoria CriarCategoria(@RequestBody Categoria categoria){
        return repository.save(categoria);
    }
    @PostMapping
    public PessoasResponseDTO criarPessoas(@RequestBody @Valid PessoasRequestDTO dto){
        Pessoas pessoaSalva = service.salvar(dto);
        return new PessoasResponseDTO(pessoaSalva);
    }
    @GetMapping
    public List<PessoasResponseDTO> listarTodos(){
        return service.listarTodos();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PessoasResponseDTO> atualizar(@PathVariable Long id,@RequestBody PessoasRequestDTO dto){
        Pessoas pessoas = service.atualizar(id, dto);
        return ResponseEntity.ok(new PessoasResponseDTO(pessoas));
    }@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/por-categoria")
    public List<PessoasResponseDTO> buscarPorCategoria(@RequestParam String nome){
        List<Pessoas> listar = pessoaRepository.findByCategoriaNome(nome);
        return listar.stream()
                .map(PessoasResponseDTO :: new)
                .toList();
    }
}
