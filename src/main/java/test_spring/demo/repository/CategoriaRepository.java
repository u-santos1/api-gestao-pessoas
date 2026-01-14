package test_spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test_spring.demo.model.Categoria;
import test_spring.demo.model.Pessoas;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}
