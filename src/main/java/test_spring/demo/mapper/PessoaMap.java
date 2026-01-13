package test_spring.demo.mapper;

import org.mapstruct.Mapper;
import test_spring.demo.DTO.PessoasRequestDTO;
import test_spring.demo.DTO.PessoasResponseDTO;
import test_spring.demo.model.Pessoas;

@Mapper(componentModel = "spring")
public interface PessoaMap {

    Pessoas toEntity(PessoasRequestDTO dto);

    PessoasResponseDTO toDTO(Pessoas pessoas);

}
