package ec.edu.espe.arqsoftware.prueba_ii_parcial.controller;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dto.EstudianteRQ;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Estudiante;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.service.EstudianteService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/estudiante")
@Tag(name = "Estudiante", description = "Gestión de estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("crear")
    private ResponseEntity crearEstudiante(@RequestBody EstudianteRQ request){
        try{
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(request.getNombre());
            estudiante.setCorreo(request.getCorreo());
            estudiante.setFechaNacimiento(request.getFechaNacimiento());
            estudiante.setPais(request.getPais());
            estudiante = this.estudianteService.createEstudiante(estudiante);
            return ResponseEntity.ok(estudiante);
        } catch (CreateException ex){
            log.warn("Ocurrió un error, se responde al usuario con código 400");
            return ResponseEntity.badRequest().build();
        }
    }
}
