package parkinson.chris.web.exception;

public class MunroValidationException extends RuntimeException {
    public MunroValidationException(){
        super();
    }

    public MunroValidationException(String message){
        super(message);
    }
}
