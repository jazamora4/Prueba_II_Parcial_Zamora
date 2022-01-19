package ec.edu.espe.arqsoftware.prueba_ii_parcial.service;

import static org.mockito.Mockito.*;

import com.mongodb.MongoWriteException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.dao.ClienteRepository;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.FindException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceTest {

    @MockBean
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;


    @Test
    void getAllTest(){
        when(clienteRepository.findAll()).thenReturn(Data.CLIENTES);
        List<Cliente> response = this.clienteService.getAll();
        Assertions.assertEquals(Data.CLIENTES,response);
    }

    @Test
    void getAllByApellidos(){
        when(clienteRepository.findAllByApellidos("Zamora Miranda")).thenReturn(Data.CLIENTES);
        List<Cliente> response = this.clienteService.getAllByApellidos("Zamora Miranda");
        Assertions.assertEquals(Data.CLIENTES,response);
    }

    @Test
    void getAllByApellidosThrowFindException(){
        when(clienteRepository.findAllByApellidos("Zamora Miranda1")).thenReturn(new ArrayList<Cliente>());
        Assertions.assertThrows(FindException.class, () -> {
            List<Cliente> response = this.clienteService.getAllByApellidos("Zamora Miranda1");
        });
    }

    @Test
    void getClienteByIdentificacion(){
        when(clienteRepository.findClienteByIdentificacion("1805129630")).thenReturn(Data.CLIENTE_001);
        Cliente cliente = this.clienteService.getClienteByIdentificacion("1805129630");
        Assertions.assertEquals(Data.CLIENTE_001,cliente);
    }

    @Test
    void getClienteByIdentificacionThrowFindException(){
        when(clienteRepository.findClienteByIdentificacion("1805129632")).thenReturn(null);
        Assertions.assertThrows(FindException.class, () -> {
            Cliente response = this.clienteService.getClienteByIdentificacion("1805129632");
        });
    }

    @Test
    void createCliente() {
        when(clienteRepository.save(Data.CLIENTE_001)).thenReturn(Data.CLIENTE_001);
        String id = this.clienteService.createCliente(Data.CLIENTE_001);
        Assertions.assertEquals(id,Data.CLIENTE_001.getId());
    }

    @Test
    void createClienteThrowMongoWriteException() {
        when(clienteRepository.save(Data.CLIENTE_001)).thenThrow(MongoWriteException.class);
        Assertions.assertThrows(CreateException.class, () -> {
            String id = this.clienteService.createCliente(Data.CLIENTE_001);
        });
    }
}