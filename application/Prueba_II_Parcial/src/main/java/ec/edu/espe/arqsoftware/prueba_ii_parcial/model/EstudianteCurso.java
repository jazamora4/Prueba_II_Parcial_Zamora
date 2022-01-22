package ec.edu.espe.arqsoftware.prueba_ii_parcial.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EstudianteCurso {
    private String codigoCurso;
    private String area;
    private String nombreCurso;
    private BigDecimal costo;
    private Integer horas;
}
