package org.lacassandra.smooshyfaces.error;

public class EntityCreationException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -687108150245214642L;

    /**
     * Create a new <pre>EntityCreationException</pre>.
     */
    public EntityCreationException() {

    }

    /**
     * Create a new <pre>EntityCreationException</pre> with the specified detail message.
     * @param message The detail message.  The detail message is saved for later retrieval
     * by the {@link Throwable.getMessage()} method.
     */
    public EntityCreationException(String message) {
        super(message);
    }

    /**
     * Create a new <pre>EntityCreationException</pre> with the specified cause.
     * @param cause The cause.  The cause is saved for later retrieval by the
     * {@link Throwable.getCause()} method.
     */
    public EntityCreationException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new <pre>EntityCreationException with the specified detail message
     * and cause.
     * @param message The detail message.  The detail message is saved for later retrieval
     * by the {@link Throwable.getMessage()} method.
     * @param cause The cause.  The cause is saved for later retrieval by the
     * {@link Throwable.getCause()} method.
     */
    public EntityCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
