package ec.edu.espe.arqsoftware.prueba_ii_parcial.service;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.Data;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.dao.CursoRepository;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.FindException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CursoServiceTest {
    @MockBean
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @Test
    public void findCursosByAreaAndNombreLikeTest(){
        List<Curso> cursos = new ArrayList<>(Arrays.asList(Data.CURSO_002));
        when(cursoRepository.findByAreaAndNombreLike("EXACTAS","MATEMATICA ")).thenReturn(cursos);
        List<Curso> response = this.cursoService.findCursosByAreaAndNombreLike("EXACTAS","MATEMATICA ");
        assertEquals(response,cursos);
    }

    @Test
    public void findCursosByAreaAndNombreLikeTestEmpty(){
        when(cursoRepository.findByAreaAndNombreLike("EXACTAS","MATEMATICA S")).thenReturn(new ArrayList<>());
        assertThrows(FindException.class, ()-> {
            List<Curso> response = this.cursoService.findCursosByAreaAndNombreLike("EXACTAS","MATEMATICA S");
        });
    }

    @Test
    public void findCursosByAreaAndNombreLikeTestOutOfDate(){
        List<Curso> cursos = new ArrayList<>(Arrays.asList(Data.CURSO_001, Data.CURSO_004));
        when(cursoRepository.findByAreaAndNombreLike("EXACTAS","MATEMATICA B")).thenReturn(cursos);
        assertThrows(FindException.class, ()-> {
            List<Curso> response = this.cursoService.findCursosByAreaAndNombreLike("EXACTAS","MATEMATICA B");
        });
    }
}