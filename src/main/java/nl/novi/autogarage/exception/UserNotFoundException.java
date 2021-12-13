package nl.novi.autogarage.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("Cannot find user " + username);
    }
    public UserNotFoundException() {
        super("User not found.");
    }

}