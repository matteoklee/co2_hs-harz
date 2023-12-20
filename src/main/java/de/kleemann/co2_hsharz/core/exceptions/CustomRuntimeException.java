package de.kleemann.co2_hsharz.core.exceptions;

/**
 * Class "CustomRuntimeException" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 17.10.2023
 */
public class CustomRuntimeException extends RuntimeException {

    /**
	 * Serial Version
	 */
	private static final long serialVersionUID = -559487244332834082L;

	/**
	 * Constructs an new {@link CustomRuntimeException}
	 * @param message - Message Payload of this Exception
	 */
	public CustomRuntimeException(String message) {
        super(message);
    }

	/** {@inheritDoc}
	 */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
