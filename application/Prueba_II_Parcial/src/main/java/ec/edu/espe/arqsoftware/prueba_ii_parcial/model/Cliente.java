package ec.edu.espe.arqsoftware.prueba_ii_parcial.model;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "clientes")
public class Cliente {
    @Id
    private String id;
    @Indexed(unique=true)
    private String identificacion;
    private String nombres;
    private String apellidos;
    private BigDecimal salario;
    private ZonedDateTime timeOfCreation;
    private List<Direccion> direcciones;
}
