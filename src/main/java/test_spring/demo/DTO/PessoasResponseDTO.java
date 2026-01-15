package test_spring.demo.DTO;



import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;

import java.util.List;


public record PessoasResponseDTO(Long id,
                                 String nome,
                                 String email,
                                 Long categoriaId) {
    public static PessoasResponseDTO toDTO(Pessoas pessoas){
        Long categoriaId = pessoas.getCategoria() != null
                ? pessoas.getCategoria().getId()
                : null;
        return new PessoasResponseDTO(
                pessoas.getId(),
                pessoas.getNome(),
                pessoas.getEmail(),
                categoriaId

        );
    }
}
