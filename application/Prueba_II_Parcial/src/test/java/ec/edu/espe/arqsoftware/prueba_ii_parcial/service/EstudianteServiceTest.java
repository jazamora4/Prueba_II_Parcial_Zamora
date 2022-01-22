package ec.edu.espe.arqsoftware.prueba_ii_parcial.service;

import com.mongodb.MongoWriteException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.Data;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.dao.EstudianteRepository;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Estudiante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EstudianteServiceTest {

    @MockBean
    private EstudianteRepository estudianteRepository;

    @Autowired
    private EstudianteService estudianteService;

    @Test
    public void createEstudiante(){
        Estudiante estudiante = new Estudiante();
        estudiante.setFechaCreacion(new Date());
        estudiante.setEstado("ACT");
        estudiante.setNombre(Data.ESTUDIANTE_001_RQ.getNombre());
        estudiante.setCorreo(Data.ESTUDIANTE_001_RQ.getCorreo());
        estudiante.setFechaNacimiento(Data.ESTUDIANTE_001_RQ.getFechaNacimiento());
        estudiante.setPais(Data.ESTUDIANTE_001_RQ.getPais());
        when(estudianteRepository.save(estudiante)).thenReturn(Data.ESTUDIANTE_001);
        Estudiante response = this.estudianteService.createEstudiante(estudiante);
        assertEquals(response,Data.ESTUDIANTE_001);
    }

    @Test
    public void createEstudianteCreateException(){
        Estudiante estudiante = new Estudiante();
        estudiante.setFechaCreacion(new Date());
        estudiante.setEstado("ACT");
        estudiante.setNombre(Data.ESTUDIANTE_001_RQ.getNombre());
        estudiante.setCorreo(Data.ESTUDIANTE_001_RQ.getCorreo());
        estudiante.setFechaNacimiento(Data.ESTUDIANTE_001_RQ.getFechaNacimiento());
        estudiante.setPais(Data.ESTUDIANTE_001_RQ.getPais());
        when(estudianteRepository.save(estudiante)).thenThrow(MongoWriteException.class);
        assertThrows(CreateException.class, () ->{
           this.estudianteService.createEstudiante(estudiante);
        });
    }

}