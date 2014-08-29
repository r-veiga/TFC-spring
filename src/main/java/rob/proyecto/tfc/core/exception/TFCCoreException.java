package rob.proyecto.tfc.core.exception;


/**
 * Excepcion propia para el control de errores en los procesos de logica.
 * 
 * @author user
 * 
 */
public class TFCCoreException extends TFCException
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;


	/**
	 * @param message
	 */
	public TFCCoreException(final String message) {
		super(message);
	}


	/**
	 * @param cause
	 */
	public TFCCoreException(final Throwable cause) {
		super(cause);
	}


	/**
	 * @param message
	 * @param cause
	 */
	public TFCCoreException(final String message, final Throwable cause) {
		super(message, cause);
	}


	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TFCCoreException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}


}
