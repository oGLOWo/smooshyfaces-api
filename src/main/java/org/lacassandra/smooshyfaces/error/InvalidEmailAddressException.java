package org.lacassandra.smooshyfaces.error;

public class InvalidEmailAddressException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -3318688828810282331L;

    /**
     * Create a new <pre>InvalidEmailAddressException</pre>.
     */
    public InvalidEmailAddressException() {

    }

    /**
     * Create a new <pre>InvalidEmailAddressException</pre> with the specified detail message.
     * @param message The detail message.  The detail message is saved for later retrieval
     * by the {@link Throwable.getMessage()} method.
     */
    public InvalidEmailAddressException(String message) {
        super(message);
    }

    /**
     * Create a new <pre>InvalidEmailAddressException</pre> with the specified cause.
     * @param cause The cause.  The cause is saved for later retrieval by the
     * {@link Throwable.getCause()} method.
     */
    public InvalidEmailAddressException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new <pre>InvalidEmailAddressException with the specified detail message
     * and cause.
     * @param message The detail message.  The detail message is saved for later retrieval
     * by the {@link Throwable.getMessage()} method.
     * @param cause The cause.  The cause is saved for later retrieval by the
     * {@link Throwable.getCause()} method.
     */
    public InvalidEmailAddressException(String message, Throwable cause) {
        super(message, cause);
    }
}
