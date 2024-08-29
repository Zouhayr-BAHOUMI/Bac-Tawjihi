package tawjih.exception;

public class UniversiteNotFoundException extends RuntimeException {

    public UniversiteNotFoundException() {
        super("University not found !");
    }
}
