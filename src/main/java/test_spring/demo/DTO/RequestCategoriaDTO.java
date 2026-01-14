package test_spring.demo.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCategoriaDTO(
        @NotNull Long pessoaId,
        @NotBlank String nome) {
}
