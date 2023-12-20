package de.kleemann.co2_hsharz.core.exceptions;

/**
 * Class "CustomIllegalArgumentException" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 18.10.2023
 */
public class CustomIllegalArgumentException extends IllegalArgumentException {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = -5109682259096981172L;

	/**
	 * Constructs an new {@link CustomIllegalArgumentException}
	 * @param message - Message Payload of this Exception
	 */
    public CustomIllegalArgumentException(String message) {
        super(message);
    }

    /** {@inheritDoc}
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
