package de.kleemann.co2_hsharz.core.exceptions;

/**
 * Class "CustomIllegalArgumentException" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 18.10.2023
 */
public class CustomIllegalArgumentException extends IllegalArgumentException {

    public CustomIllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
