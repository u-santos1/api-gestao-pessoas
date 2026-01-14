package test_spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test_spring.demo.model.Pessoas;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoas, Long>{
    List<Pessoas> findByCategoriaNome(String nomeCategoria);
    @Query("""
    SELECT p FROM Pessoas p
    JOIN FETCH p.categoria
""")
    List<Pessoas> findAllComCategoria();



}