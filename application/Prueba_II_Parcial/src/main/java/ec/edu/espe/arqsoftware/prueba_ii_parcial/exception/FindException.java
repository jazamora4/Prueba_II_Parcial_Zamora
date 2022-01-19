package ec.edu.espe.arqsoftware.prueba_ii_parcial.exception;

public class FindException extends RuntimeException{
    public FindException() {
    }

    public FindException(String message) {
        super(message);
    }

    public FindException(String message, Throwable cause) {
        super(message, cause);
    }
}
