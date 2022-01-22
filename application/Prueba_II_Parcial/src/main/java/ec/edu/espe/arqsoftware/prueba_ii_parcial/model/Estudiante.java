package ec.edu.espe.arqsoftware.prueba_ii_parcial.model;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "estudiantes")
public class Estudiante {
    @Id
    private String id;
    private String nombre;
    @Indexed(unique=true)
    private String correo;
    private String pais;
    private Date fechaNacimiento;
    private Date fechaCreacion;
    private String estado;
}
