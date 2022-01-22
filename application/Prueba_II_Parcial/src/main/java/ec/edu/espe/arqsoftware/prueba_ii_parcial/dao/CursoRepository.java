package ec.edu.espe.arqsoftware.prueba_ii_parcial.dao;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CursoRepository extends MongoRepository<Curso, String> {
    List<Curso> findByAreaAndNombreLike(String area, String nombre);
}
