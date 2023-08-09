package parking.common.exception;

public abstract class AbstractBaseException extends RuntimeException {
    public AbstractBaseException() {
    }

    public AbstractBaseException(String message) {
        super(message);
    }

    public AbstractBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
