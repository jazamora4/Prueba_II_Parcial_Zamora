package ec.edu.espe.arqsoftware.prueba_ii_parcial.controller;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dto.ClienteRQ;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.CreateException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.exception.FindException;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Cliente;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Endpoints de prueba")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("todos")
    private ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> clientes = this.clienteService.getAll();
        if(!clientes.isEmpty()){
            return ResponseEntity.ok(clientes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("apellidos/{apellidos}")
    private ResponseEntity<List<Cliente>> getClientesByApellidos(@PathVariable("apellidos") String apellidos){
        try{
            List<Cliente> clientes = this.clienteService.getAllByApellidos(apellidos);
            return ResponseEntity.ok(clientes);
        } catch (FindException ex){
            log.info("{}. Se responde al usuario con el código {}",ex.getMessage(),"404");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("identificacion/{identificacion}")
    private ResponseEntity<Cliente> getClienteByIndentificacion(@PathVariable("identificacion") String identificacion){
        try{
            Cliente cliente = this.clienteService.getClienteByIdentificacion(identificacion);
            return ResponseEntity.ok(cliente);
        } catch (FindException ex){
            log.info("{}. Se responde al usuario con el código {}",ex.getMessage(),"404");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<String> createCliente(@RequestBody ClienteRQ request){
        try{
            Cliente cliente = new Cliente();
            cliente.setIdentificacion(request.getIdentificacion());
            cliente.setApellidos(request.getApellidos());
            cliente.setNombres(request.getNombres());
            cliente.setSalario(request.getSalario());
            cliente.setDirecciones(request.getDirecciones());
            String id = this.clienteService.createCliente(cliente);
            return ResponseEntity.ok(id);
        } catch (CreateException ex){
            log.info("{}. Se responde al usuario con el código {}",ex.getMessage(),"400");
            return ResponseEntity.badRequest().build();
        }
    }
}
