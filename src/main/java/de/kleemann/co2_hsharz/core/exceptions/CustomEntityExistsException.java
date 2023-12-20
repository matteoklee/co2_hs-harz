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

    /**
	 * Serial Version
	 */
	private static final long serialVersionUID = -1797282454792413316L;

	/**
	 * Constructs an new {@link CustomEntityExistsException}
	 * @param message - Message Payload of this Exception
	 */
	public CustomEntityExistsException(String message) {
        super(message);
    }

	/** {@inheritDoc}
	 */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
