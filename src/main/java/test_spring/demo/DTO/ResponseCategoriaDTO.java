package test_spring.demo.DTO;

import test_spring.demo.model.Categoria;

public record ResponseCategoriaDTO(Long id, String nome) {
    public static ResponseCategoriaDTO fromEntity(Categoria categoria) {

        return new ResponseCategoriaDTO(
                categoria.getId(),
                categoria.getNome()
        );

    }
}

