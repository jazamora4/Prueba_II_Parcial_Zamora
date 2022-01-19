package ec.edu.espe.arqsoftware.prueba_ii_parcial.exception;

public class CreateException extends RuntimeException{
    public CreateException() {
    }

    public CreateException(String message) {
        super(message);
    }

    public CreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
