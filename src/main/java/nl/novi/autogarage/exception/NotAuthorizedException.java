package nl.novi.autogarage.exception;


public class NotAuthorizedException extends RuntimeException {


    public NotAuthorizedException(String message) {
        super(message);
    }
    public NotAuthorizedException() {
        super("Not authorized.");
    }
}