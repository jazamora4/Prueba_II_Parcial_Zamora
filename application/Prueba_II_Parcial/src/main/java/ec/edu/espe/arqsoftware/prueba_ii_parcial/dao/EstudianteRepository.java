package ec.edu.espe.arqsoftware.prueba_ii_parcial.dao;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {

}
