package de.kleemann.co2_hsharz.core.exceptions;

import jakarta.persistence.EntityExistsException;

/**
 * Class "CustomEntityExistsException" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 18.10.2023
 */
public class CustomEntityExistsException extends EntityExistsException {

    public CustomEntityExistsException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
