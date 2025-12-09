package test_spring.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PessoasRequestDTO {
    @NotBlank
    private String nome;
    @Email
    private String email;

    private Long categoriaId;
}
