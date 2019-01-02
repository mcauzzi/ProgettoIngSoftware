package Controller;

public class InvalidUserException extends Throwable {
    InvalidUserException(String message) {
        super(message);
    }
}
