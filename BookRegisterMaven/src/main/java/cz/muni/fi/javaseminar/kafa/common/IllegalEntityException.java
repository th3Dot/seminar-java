package cz.muni.fi.javaseminar.kafa.common;

/**
 * This exception is thrown when you try to use an entity that can not be 
 * used for the operation.
 * 
 * @author Martin Kalenda
 */
public class IllegalEntityException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>IllegalEntityException</code> without detail message.
     */
    public IllegalEntityException() {
    }

    /**
     * Constructs an instance of
     * <code>IllegalEntityException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public IllegalEntityException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of
     * <code>IllegalEntityException</code> with the specified detail
     * message and cause.
     * 
     * @param message the detail message.
     * @param cause the cause
     */
    public IllegalEntityException(String message, Throwable cause) {
        super(message, cause);
    }
        
}
