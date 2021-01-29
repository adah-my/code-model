package model.serialization.exception;

public class SerializeException extends RuntimeException {
    private static final long serialVersionUID = -6589127357170440567L;

    public SerializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
