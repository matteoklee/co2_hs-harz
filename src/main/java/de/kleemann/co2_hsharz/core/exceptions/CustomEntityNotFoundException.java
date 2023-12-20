package de.kleemann.co2_hsharz.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

/**
 * Class "CustomEntityNotFoundException" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 18.10.2023
 */
public class CustomEntityNotFoundException extends EntityNotFoundException {

    /**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an new {@link CustomEntityNotFoundException}
	 * @param message - Message Payload of this Exception
	 */
	public CustomEntityNotFoundException(String message) {
        super(message);
    }

	/** {@inheritDoc}
	 */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
