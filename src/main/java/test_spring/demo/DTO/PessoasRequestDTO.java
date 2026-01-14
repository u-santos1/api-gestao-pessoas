package test_spring.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record PessoasRequestDTO (
        @NotNull Long categoriaId,
    @NotBlank String nome,
    @Email String email)
{}
