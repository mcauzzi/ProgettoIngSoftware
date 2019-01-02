package Controller;

public class InvalidPasswordException extends Throwable {
    InvalidPasswordException(String message) {
        super(message);
    }
}
