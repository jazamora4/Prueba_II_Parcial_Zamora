package ec.edu.espe.arqsoftware.prueba_ii_parcial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.Data;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.dto.CursoFindRQ;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.FindException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Curso;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.service.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CursoService cursoService;

    @Test
    public void obtenerCursosPorAreaYNombreTest() throws Exception {
        CursoFindRQ request = new CursoFindRQ();
        request.setNombre("MATEMATICA ");
        request.setArea("EXACTAS");
        List<Curso> cursos = new ArrayList<>(List.of(Data.CURSO_002));
        when(this.cursoService.findCursosByAreaAndNombreLike(request.getArea(),request.getNombre())).thenReturn(cursos);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBody = ow.writeValueAsString(request);
        mvc.perform(post("/api/v1/curso/consultar/area/nombre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void obtenerCursosPorAreaYNombreTestNotFound() throws Exception {
        CursoFindRQ request = new CursoFindRQ();
        request.setNombre("MATEMATICA S");
        request.setArea("EXACTAS");
        List<Curso> cursos = new ArrayList<>();
        when(this.cursoService.findCursosByAreaAndNombreLike(request.getArea(),request.getNombre())).thenThrow(FindException.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBody = ow.writeValueAsString(request);
        mvc.perform(post("/api/v1/curso/consultar/area/nombre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }
}
