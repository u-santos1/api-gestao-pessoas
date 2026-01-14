package test_spring.demo.DTO;


import jakarta.validation.constraints.NotNull;

public record RequestCategoriaDTO(
        Long id, @NotNull
        String nome,
        Long PessoaId) {
}
