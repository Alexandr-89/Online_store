package by.overone.online_shop.validator.exception;

public class ValidatorException extends RuntimeException{

    public ValidatorException() {
        super();
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
