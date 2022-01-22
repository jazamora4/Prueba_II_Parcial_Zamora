package ec.edu.espe.arqsoftware.prueba_ii_parcial.controller;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dto.CursoFindRQ;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.FindException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Curso;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.service.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/curso")
@Tag(name = "Curso", description = "Gestión de cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }


    @PostMapping("consultar/area/nombre")
    public ResponseEntity obtenerCursosPorAreaYNombre(@RequestBody CursoFindRQ request){
        try{
            return ResponseEntity.ok(this.cursoService.findCursosByAreaAndNombreLike(request.getArea(), request.getNombre()));
        } catch (FindException ex){
            log.warn("Ocurrió un error, se responde al usuario con código 404");
            return ResponseEntity.notFound().build();
        }

    }

}
