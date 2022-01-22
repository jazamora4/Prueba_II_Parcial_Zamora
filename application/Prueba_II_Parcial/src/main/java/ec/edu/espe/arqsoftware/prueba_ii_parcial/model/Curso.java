package ec.edu.espe.arqsoftware.prueba_ii_parcial.model;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "cursos")
public class Curso {
    @Id
    private String id;
    @Indexed(unique=true)
    private String codigo;
    private String area;
    private String nombre;
    private Integer duracionHoras;
    private Date fechaInicio;
    private BigDecimal costo;
}
