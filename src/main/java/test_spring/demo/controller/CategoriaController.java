package test_spring.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test_spring.demo.DTO.PessoasResponseDTO;
import test_spring.demo.DTO.RequestCategoriaDTO;
import test_spring.demo.DTO.ResponseCategoriaDTO;
import test_spring.demo.Servico.PessoasService;
import test_spring.demo.model.Categoria;
import test_spring.demo.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    final private PessoasService service;
    public CategoriaController(PessoasService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseCategoriaDTO> salvar(@RequestBody @Valid RequestCategoriaDTO data){
        ResponseCategoriaDTO dto = service.criarCategoria(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
