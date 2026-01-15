package test_spring.demo.DTO.infra;

import org.hibernate.type.descriptor.java.LocaleJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import test_spring.demo.DTO.ErroDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class TratadorDeErro {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RecursoNaoEncotradoException.class)
    public ErroDTO tratarErro404(RecursoNaoEncotradoException ex){
        return new ErroDTO("id", ex.getMessage());
    }
    public List<ErroDTO> handle(MethodArgumentNotValidException exception){

        List<ErroDTO> dto = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(e -> {
        String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
        ErroDTO erro = new ErroDTO(e.getField(), mensagem);
        dto.add(erro);
        });
        return dto;
    }
}
