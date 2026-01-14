package test_spring.demo.DTO;



import lombok.Data;
import test_spring.demo.model.Pessoas;

@Data
public class PessoasResponseDTO {
    private Long id;
    private String nome;
    private String email;

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

    public PessoasResponseDTO (Pessoas pessoas){
        this.id = pessoas.getId();
        this.nome = pessoas.getNome();
        this.email = pessoas.getEmail();

    }
}
