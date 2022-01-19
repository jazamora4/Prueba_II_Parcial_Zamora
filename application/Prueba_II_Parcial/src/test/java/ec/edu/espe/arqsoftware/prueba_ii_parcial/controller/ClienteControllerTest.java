package ec.edu.espe.arqsoftware.prueba_ii_parcial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.Data;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.FindException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Cliente;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void getAllClientesTest() throws Exception {
        when(clienteService.getAll()).thenReturn(Data.CLIENTES);
        mvc.perform(get("/api/v1/clientes/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(Data.CLIENTE_001.getId()));
    }

    @Test
    void getAllClientesTestNotFoundClientes() throws Exception {
        when(clienteService.getAll()).thenReturn(new ArrayList<Cliente>());
        mvc.perform(get("/api/v1/clientes/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getClientesByApellidosTest() throws Exception{
        when(clienteService.getAllByApellidos("Zamora Miranda")).thenReturn(Data.CLIENTES);
        mvc.perform(get("/api/v1/clientes/apellidos/Zamora Miranda")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(Data.CLIENTE_001.getId()));
    }

    @Test
    void getClientesByApellidosTestNotFound() throws Exception{
        when(clienteService.getAllByApellidos("Zamora Miranda")).thenThrow(FindException.class);
        mvc.perform(get("/api/v1/clientes/apellidos/Zamora Miranda")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getClienteByIndentificacionTest() throws Exception{
        when(clienteService.getClienteByIdentificacion("1805129630")).thenReturn(Data.CLIENTE_001);
        mvc.perform(get("/api/v1/clientes/identificacion/1805129630")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(Data.CLIENTE_001.getId()));
    }

    @Test
    void getClienteByIndentificacionTestNotFound() throws Exception{
        when(clienteService.getClienteByIdentificacion("1805129632")).thenThrow(FindException.class);
        mvc.perform(get("/api/v1/clientes/identificacion/1805129632")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createClienteTest() throws Exception {
        Cliente request = new Cliente();
        request.setIdentificacion(Data.CLIENTE_RQ_001.getIdentificacion());
        request.setNombres(Data.CLIENTE_RQ_001.getNombres());
        request.setApellidos(Data.CLIENTE_RQ_001.getApellidos());
        request.setDirecciones(Data.CLIENTE_RQ_001.getDirecciones());
        request.setSalario(Data.CLIENTE_RQ_001.getSalario());
        when(clienteService.createCliente(request)).thenReturn(Data.CLIENTE_001.getId());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBody = ow.writeValueAsString(Data.CLIENTE_RQ_001);
        mvc.perform(post("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("123abc456"));
    }

    @Test
    void createClienteTestCreateExeption() throws Exception {
        Cliente request = new Cliente();
        request.setIdentificacion(Data.CLIENTE_RQ_001.getIdentificacion());
        request.setNombres(Data.CLIENTE_RQ_001.getNombres());
        request.setApellidos(Data.CLIENTE_RQ_001.getApellidos());
        request.setDirecciones(Data.CLIENTE_RQ_001.getDirecciones());
        request.setSalario(Data.CLIENTE_RQ_001.getSalario());
        when(clienteService.createCliente(request)).thenThrow(CreateException.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBody = ow.writeValueAsString(Data.CLIENTE_RQ_001);
        mvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}