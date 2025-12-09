package test_spring.demo.DTO.infra;

public class RecursoNaoEncotradoException extends RuntimeException{

    public RecursoNaoEncotradoException(String mensagem){
        super(mensagem);
    }
}
