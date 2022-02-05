package nl.novi.autogarage.exception;

public class FileNotFoundException extends RuntimeException {

    private String msg;

    public FileNotFoundException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
