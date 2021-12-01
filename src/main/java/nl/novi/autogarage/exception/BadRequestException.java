package nl.novi.autogarage.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        //Als je overerft moet je hem doorgeven naar z'n parent class
        super();
    }
    public BadRequestException(String message) {
        super(message);
    }
}
