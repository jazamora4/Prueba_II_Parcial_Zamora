package ec.edu.espe.arqsoftware.prueba_ii_parcial.dto;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Direccion;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ClienteRQ {
    private String identificacion;
    private String nombres;
    private String apellidos;
    private BigDecimal salario;
    private List<Direccion> direcciones;
}
