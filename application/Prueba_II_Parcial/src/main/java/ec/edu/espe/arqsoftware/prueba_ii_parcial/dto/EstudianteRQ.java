package ec.edu.espe.arqsoftware.prueba_ii_parcial.dto;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EstudianteRQ {
    private String nombre;
    private String correo;
    private String pais;
    private Date fechaNacimiento;
}
