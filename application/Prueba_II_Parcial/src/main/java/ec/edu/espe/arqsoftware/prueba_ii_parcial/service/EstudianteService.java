package ec.edu.espe.arqsoftware.prueba_ii_parcial.service;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dao.EstudianteRepository;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Estudiante;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public Estudiante createEstudiante(Estudiante estudiante) throws CreateException{
        try{
            estudiante.setFechaCreacion(new Date());
            estudiante.setEstado("ACT");
            Estudiante response = this.estudianteRepository.save(estudiante);
            return response;
        } catch (Exception ex) {
            log.error("No se ha podido guardar el estudiante {} debido a {}", estudiante, ex.getMessage());
            throw new CreateException("Error al crear un estudiante, causado por: "+ex.getMessage());
        }
    }
}
