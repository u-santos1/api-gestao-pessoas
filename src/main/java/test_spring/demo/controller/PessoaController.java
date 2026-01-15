package test_spring.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.DTO.PessoasResponseDTO;
import test_spring.demo.Servico.PessoasService;
import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;
import test_spring.demo.repository.CategoriaRepository;
import test_spring.demo.repository.PessoaRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    final private PessoaRepository pessoaRepository;
    final private PessoasService service;

    public PessoaController(PessoaRepository pessoaRepository, PessoasService service){
        this.pessoaRepository = pessoaRepository;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PessoasResponseDTO> criarPessoas(@RequestBody @Valid PessoasRequestDTO dto){
        PessoasResponseDTO pessoaSalva = service.salvar(dto);
        URI location = URI.create("/api/pessoas/" + pessoaSalva.id());
        return ResponseEntity.created(location).body(pessoaSalva);
    }
    @GetMapping
    public ResponseEntity<List<PessoasResponseDTO>> listarTodos(){
        List<PessoasResponseDTO> dtos = service.listarTodos();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoasResponseDTO> atualizar(@PathVariable Long id,@RequestBody PessoasRequestDTO data){
        PessoasResponseDTO dto = service.atualizar(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);


    }@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/por-categoria")
    public List<PessoasResponseDTO> buscarPorCategoria(@RequestParam String nome){
        List<Pessoas> listar = pessoaRepository.findByCategoriaNome(nome);
        return listar.stream()
                .map(PessoasResponseDTO :: toDTO)
                .toList();
    }
}
