package tawjih.exception;

public class QuestionNotFoundException extends RuntimeException{

    public QuestionNotFoundException() {

        super("Question not found !");
    }
}
