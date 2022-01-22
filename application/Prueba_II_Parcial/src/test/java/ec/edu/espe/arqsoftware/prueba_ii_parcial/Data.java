package ec.edu.espe.arqsoftware.prueba_ii_parcial;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dto.EstudianteRQ;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Curso;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Estudiante;

import java.math.BigDecimal;
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

    public static Curso CURSO_001 = null;
    static {
        try {
            CURSO_001 = new Curso(
                    "abc123abc",
                    "MAT1",
                    "EXACTAS",
                    "MATEMATICA BASICA",
                    125,
                    new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-23"),
                    new BigDecimal("125.36")
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Curso CURSO_002 = null;
    static {
        try {
            CURSO_002 = new Curso(
                    "abc123abd",
                    "MAT2",
                    "EXACTAS",
                    "MATEMATICA SUPERIOR",
                    125,
                    new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-22"),
                    new BigDecimal("125.36")
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Curso CURSO_003 = null;
    static {
        try {
            CURSO_003 = new Curso(
                    "abc123abB",
                    "COMP1",
                    "COMPUTACION",
                    "EXCEL BASICO",
                    125,
                    new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-25"),
                    new BigDecimal("125.36")
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
