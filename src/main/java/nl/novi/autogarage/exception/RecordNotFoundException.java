package nl.novi.autogarage.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        //Als je overerft moet je hem doorgeven naar z'n parent class
        super();
    }
    public RecordNotFoundException(String message) {
        super(message);
    }
}
