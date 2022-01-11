package nl.novi.autogarage.exception;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException() {

        super();
    }
    public FileNotFoundException(String message) {
        super(message);
    }
}

