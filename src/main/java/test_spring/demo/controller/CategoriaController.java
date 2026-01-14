package test_spring.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import test_spring.demo.DTO.RequestCategoriaDTO;
import test_spring.demo.DTO.ResponseCategoriaDTO;
import test_spring.demo.Servico.CategoriaService;
import test_spring.demo.Servico.PessoasService;

import java.util.List;


@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    final private CategoriaService categoriaService;
    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<ResponseCategoriaDTO> salvar(@RequestBody @Valid RequestCategoriaDTO data){
        ResponseCategoriaDTO dto = categoriaService.criarCategoria(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @GetMapping
    public ResponseEntity<List<ResponseCategoriaDTO>> listaTodos(){
        List<ResponseCategoriaDTO> data = categoriaService.listarTodos();
        return ResponseEntity.ok(data);
    }
}
