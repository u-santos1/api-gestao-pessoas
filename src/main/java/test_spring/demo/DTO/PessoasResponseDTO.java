package test_spring.demo.DTO;



import lombok.Data;
import test_spring.demo.model.Pessoas;

@Data
public class PessoasResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String nomeCategoria;

    public PessoasResponseDTO (Pessoas pessoas){
        this.id = pessoas.getId();
        this.nome = pessoas.getNome();
        this.email = pessoas.getEmail();

        if (pessoas.getCategoria() != null){
            this.nomeCategoria = pessoas.getCategoria().getNome();
        }
    }
}
