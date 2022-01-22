package ec.edu.espe.arqsoftware.prueba_ii_parcial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.Data;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Estudiante;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EstudianteService estudianteService;

    @Test
    public void crearEstudianteTest() throws Exception{
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(Data.ESTUDIANTE_001_RQ.getNombre());
        estudiante.setPais(Data.ESTUDIANTE_001_RQ.getPais());
        estudiante.setFechaNacimiento(Data.ESTUDIANTE_001_RQ.getFechaNacimiento());
        estudiante.setCorreo(Data.ESTUDIANTE_001_RQ.getCorreo());
        when(estudianteService.createEstudiante(estudiante)).thenReturn(Data.ESTUDIANTE_001);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBody = ow.writeValueAsString(Data.ESTUDIANTE_001_RQ);
        mvc.perform(post("/api/v1/estudiante/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void crearEstudianteTestCreateException() throws Exception{
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(Data.ESTUDIANTE_001_RQ.getNombre());
        estudiante.setPais(Data.ESTUDIANTE_001_RQ.getPais());
        estudiante.setFechaNacimiento(Data.ESTUDIANTE_001_RQ.getFechaNacimiento());
        estudiante.setCorreo(Data.ESTUDIANTE_001_RQ.getCorreo());
        when(estudianteService.createEstudiante(estudiante)).thenThrow(CreateException.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBody = ow.writeValueAsString(Data.ESTUDIANTE_001_RQ);
        mvc.perform(post("/api/v1/estudiante/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}