package ec.edu.espe.arqsoftware.prueba_ii_parcial.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EstudianteCurso {
    private String idEstudiante;
    private String nombreEstudiante;
    private String paisEstudiante;
    private String correoEstudiante;
}
