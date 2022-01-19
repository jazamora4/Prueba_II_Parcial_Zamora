package ec.edu.espe.arqsoftware.prueba_ii_parcial.dao;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClienteRepository extends MongoRepository<Cliente,String> {
    Cliente findClienteByIdentificacion(String identificacion);
    List<Cliente> findAllByApellidos(String apellidos);
}
