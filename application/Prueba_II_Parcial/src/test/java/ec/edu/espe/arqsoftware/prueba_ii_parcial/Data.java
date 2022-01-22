package ec.edu.espe.arqsoftware.prueba_ii_parcial;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dto.EstudianteRQ;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Estudiante;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Data {
    public static Estudiante ESTUDIANTE_001 = null;

    static {
        try {
            ESTUDIANTE_001 = new Estudiante(
                    "123abc456",
                    "Jose Andres",
                    "jazamora4@espe.edu.ec",
                    "ECU",
                    new SimpleDateFormat("yyyy-MM-dd").parse("1999-04-21"),
                    new Date(),
                    "ACT"

            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Estudiante ESTUDIANTE_002 = null;

    static {
        try {
            ESTUDIANTE_002 = new Estudiante(
                    "123abc457",
                    "Juan Andres",
                    "jazamora5@espe.edu.ec",
                    "ECU",
                    new SimpleDateFormat("yyyy-MM-dd").parse("1999-04-21"),
                    new Date(),
                    "ACT"

            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static EstudianteRQ ESTUDIANTE_001_RQ = null;
    static {
        try {
            ESTUDIANTE_001_RQ = new EstudianteRQ(
                    "Jose Andres",
                    "jazamora4@espe.edu.ec",
                    "ECU",
                    new SimpleDateFormat("yyyy-MM-dd").parse("1999-04-21")
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
