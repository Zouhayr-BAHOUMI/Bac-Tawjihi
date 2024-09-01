package tawjih.exception;

public class EtablissementNotFoundException extends RuntimeException{

    public EtablissementNotFoundException() {

        super("Etablissement not found !");
    }
}
