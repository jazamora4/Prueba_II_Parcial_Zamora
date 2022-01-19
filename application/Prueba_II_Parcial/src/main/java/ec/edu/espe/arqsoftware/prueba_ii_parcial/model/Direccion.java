package ec.edu.espe.arqsoftware.prueba_ii_parcial.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Direccion {
    private String ciudad;
    private String provincia;
    private String pais;
    private String calle;
}
