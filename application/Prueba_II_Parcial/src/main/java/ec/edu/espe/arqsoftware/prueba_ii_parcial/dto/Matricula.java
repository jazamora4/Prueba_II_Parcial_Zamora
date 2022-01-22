package ec.edu.espe.arqsoftware.prueba_ii_parcial.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Matricula {
    private String correo;
    private String nombreEstudiante;
    private String area;
    private String nombreCurso;
    private BigDecimal costo;
    private Date fechaInici;
}
