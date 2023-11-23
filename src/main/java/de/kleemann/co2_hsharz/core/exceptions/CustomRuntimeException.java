package de.kleemann.co2_hsharz.core.exceptions;

/**
 * Class "CustomRuntimeException" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 17.10.2023
 */
public class CustomRuntimeException extends RuntimeException {

    public CustomRuntimeException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
