package ec.edu.espe.arqsoftware.prueba_ii_parcial.service;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dao.ClienteRepository;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.FindException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAll(){
        return this.clienteRepository.findAll();
    }

    public List<Cliente> getAllByApellidos(String apellidos) throws FindException{
        List<Cliente> response = this.clienteRepository.findAllByApellidos(apellidos);
        if(!response.isEmpty()){
            return response;
        } else {
            log.warn("No se ha encontrado clientes con los apellidos: {}",apellidos);
            throw new FindException("No se ha encontrado ningun cliente con los apellidos: "+apellidos);
        }
    }

    public Cliente getClienteByIdentificacion(String identificacion) throws FindException{
        Cliente cliente = this.clienteRepository.findClienteByIdentificacion(identificacion);
        if(cliente!=null){
            return cliente;
        } else {
            log.warn("No se ha encontrado un cliente con la identificacion: {}", identificacion);
            throw new FindException("No se ha encontrado ningun cliente con la identificacion: "+identificacion);
        }
    }

    public String createCliente(Cliente cliente) throws CreateException {
        try{
            cliente.setTimeOfCreation(ZonedDateTime.now(ZoneId.of("America/Guayaquil")));
            log.info("Se realizará la creación del cliente con datos {}",cliente);
            cliente = this.clienteRepository.save(cliente);
            return cliente.getId();
        } catch (Exception ex){
            log.error("No se ha podido guardar el cliente con datos: {}, provocado por: {}",cliente,ex.getMessage());
            throw new CreateException("No se ha podido guardar el cliente, provocado por: "+ex.getMessage(),ex);
        }
    }
}
