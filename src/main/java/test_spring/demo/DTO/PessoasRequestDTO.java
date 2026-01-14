package test_spring.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


public record PessoasRequestDTO (
    @NotBlank String nome,
    @Email String email,
    Long categoriaId)
{}
