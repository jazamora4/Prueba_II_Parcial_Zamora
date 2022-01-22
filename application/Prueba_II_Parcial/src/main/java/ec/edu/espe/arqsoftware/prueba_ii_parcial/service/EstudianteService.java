package ec.edu.espe.arqsoftware.prueba_ii_parcial.service;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dao.CursoRepository;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.dao.EstudianteRepository;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.dto.Matricula;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Curso;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Estudiante;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.EstudianteCurso;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
@Slf4j
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public EstudianteService(EstudianteRepository estudianteRepository, CursoRepository cursoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    public Estudiante createEstudiante(Estudiante estudiante) throws CreateException{
        try{
            estudiante.setFechaCreacion(new Date());
            estudiante.setEstado("ACT");
            estudiante.setCursos(new ArrayList<>());
            Estudiante response = this.estudianteRepository.save(estudiante);
            return response;
        } catch (Exception ex) {
            log.error("No se ha podido guardar el estudiante {} debido a {}", estudiante, ex.getMessage());
            throw new CreateException("Error al crear un estudiante, causado por: "+ex.getMessage());
        }
    }

    public Matricula matricularEstudiante(String cursoCodigo, String correo) throws CreateException{
        Matricula matricula = new Matricula();
        Estudiante estudiante = this.estudianteRepository.findByCorreo(correo);
        if(estudiante==null){
            log.error("El estudiante no existe con el correo {}",correo);
            throw new CreateException("El estudiante no existe");
        }
        if(estudiante.getCursos().size()<2){
            Curso curso = cursoRepository.findByCodigo(cursoCodigo);
            if(curso==null){
                log.error("El curso no existe con el codigo {}",cursoCodigo);
                throw new CreateException("El curso no existe");
            }
            if(estudiante.getCursos().isEmpty()){
                EstudianteCurso cursoEstudiante = new EstudianteCurso();
                cursoEstudiante.setCodigoCurso(curso.getCodigo());
                cursoEstudiante.setNombreCurso(curso.getNombre());
                cursoEstudiante.setArea(curso.getArea());
                cursoEstudiante.setCosto(curso.getCosto());
                cursoEstudiante.setHoras(curso.getDuracionHoras());
                estudiante.getCursos().add(cursoEstudiante);
                this.estudianteRepository.save(estudiante);
                matricula.setArea(curso.getArea());
                matricula.setCorreo(estudiante.getCorreo());
                matricula.setCosto(curso.getCosto());
                matricula.setNombreCurso(curso.getNombre());
                matricula.setNombreEstudiante(estudiante.getNombre());
                matricula.setFechaInici(curso.getFechaInicio());
                return matricula;
            } else {
                for (EstudianteCurso cursoEstudiante:
                        estudiante.getCursos()) {
                    if(!cursoEstudiante.getCodigoCurso().equals(cursoCodigo)){
                        EstudianteCurso cursoEstudiante1 = new EstudianteCurso();
                        cursoEstudiante1.setCodigoCurso(curso.getCodigo());
                        cursoEstudiante1.setNombreCurso(curso.getNombre());
                        cursoEstudiante1.setArea(curso.getArea());
                        cursoEstudiante1.setCosto(curso.getCosto());
                        cursoEstudiante1.setHoras(curso.getDuracionHoras());
                        estudiante.getCursos().add(cursoEstudiante1);
                        this.estudianteRepository.save(estudiante);
                        matricula.setArea(curso.getArea());
                        matricula.setCorreo(estudiante.getCorreo());
                        matricula.setCosto(curso.getCosto());
                        matricula.setNombreCurso(curso.getNombre());
                        matricula.setNombreEstudiante(estudiante.getNombre());
                        matricula.setFechaInici(curso.getFechaInicio());
                        return matricula;
                    } else {
                        log.error("El estudiante {} ya esta en el curso {}",estudiante,curso);
                        throw new CreateException("El estudiante ya esta en ese curso");
                    }
                }
            }
        } else {
            log.error("El estudiante {} ya esta en mas de dos cursos",estudiante);
            throw new CreateException("El estudiante ya tiene más de dos matriculas");
        }
        return matricula;
    }
}
