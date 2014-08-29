package rob.proyecto.tfc.core.exception;


/**
 * Excepcion propia global.
 * 
 * @author user
 * 
 */
public class TFCException extends Exception
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;


	/**
	 * @param message
	 */
	public TFCException(final String message) {
		super(message);
	}


	/**
	 * @param cause
	 */
	public TFCException(final Throwable cause) {
		super(cause);
	}


	/**
	 * @param message
	 * @param cause
	 */
	public TFCException(final String message, final Throwable cause) {
		super(message, cause);
	}


	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TFCException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}


}
