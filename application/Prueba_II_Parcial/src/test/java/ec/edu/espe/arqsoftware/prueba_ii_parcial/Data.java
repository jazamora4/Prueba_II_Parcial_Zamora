package ec.edu.espe.arqsoftware.prueba_ii_parcial;

import ec.edu.espe.arqsoftware.prueba_ii_parcial.dto.ClienteRQ;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Cliente;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.model.Direccion;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static final Direccion DIRECCION_001 = new Direccion(
            "Ambato",
            "Tungurahua",
            "Ecuador",
            "Av. Cevallos"
    );

    public static final Direccion DIRECCION_002 = new Direccion(
            "Ambato",
            "Tungurahua",
            "Ecuador",
            "Av. 12 de Noviembre"
    );

    public static final List<Direccion> DIRECCIONES = new ArrayList<>(Arrays.asList(DIRECCION_001,DIRECCION_002));

    public static final Cliente CLIENTE_001 = new Cliente(
            "123abc456",
            "1805129630",
            "José Andrés",
            "Zamora Miranda",
            new BigDecimal("35.25"),
            ZonedDateTime.parse("2020-01-19T14:28:11.252+05:00[America/Guayaquil]"),
            DIRECCIONES
    );

    public static final Cliente CLIENTE_002 = new Cliente(
            "123abc457",
            "1805129631",
            "José Andrés",
            "Zamora Miranda",
            new BigDecimal("354.25"),
            ZonedDateTime.parse("2020-01-19T14:38:11.252+05:00[America/Guayaquil]"),
            DIRECCIONES
    );

    public static final ClienteRQ CLIENTE_RQ_001 = new ClienteRQ(
            "1805129630",
            "José Andrés",
            "Zamora Miranda",
            new BigDecimal("354.25"),
            DIRECCIONES
    );

    public static final List<Cliente> CLIENTES = new ArrayList<>(
            Arrays.asList(CLIENTE_001,CLIENTE_002)
    );
}
