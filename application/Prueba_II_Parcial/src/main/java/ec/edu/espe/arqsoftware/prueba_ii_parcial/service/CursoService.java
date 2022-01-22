package ec.edu.espe.arqsoftware.prueba_ii_parcial.service;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dao.CursoRepository;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.FindException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Curso;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }


    public List<Curso> findCursosByAreaAndNombreLike(String area, String nombre) throws FindException{
        List<Curso> cursos = this.cursoRepository.findByAreaAndNombreLike(area, nombre);
        List<Curso> response = new ArrayList<>();
        Date actualDate = new Date();
        Date dt = this.futureDate(1);
        if(!cursos.isEmpty()){
            for (Curso curso:
                 cursos) {
                if(!curso.getFechaInicio().before(actualDate)
                        && !curso.getFechaInicio().after(dt)){
                    response.add(curso);
                }
            }
            if(!response.isEmpty()){
                return response;
            } else {
                log.warn("Ningun curso coincide con las fechas establecidas {} y {}",area,nombre);
                throw new FindException("No existe ningun curso en las fechas dadas");
            }
        } else {
            log.warn("Ningun curso coincide con los parametros {} y {}",area,nombre);
            throw new FindException("No existe ningun elemento que coincida con los parametros dados");
        }
    }

    private Date futureDate(Integer numOfDays){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, numOfDays);
        dt = c.getTime();
        return dt;
    }


}
