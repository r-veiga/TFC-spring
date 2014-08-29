package rob.proyecto.tfc.core.exception;


/**
 * Excepcion propia para el control de errores en acceso a base de datos.
 * 
 * @author user
 * 
 */
public class TFCSQLException extends TFCException
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;


	/**
	 * @param message
	 */
	public TFCSQLException(final String message) {
		super(message);
	}


	/**
	 * @param cause
	 */
	public TFCSQLException(final Throwable cause) {
		super(cause);
	}


	/**
	 * @param message
	 * @param cause
	 */
	public TFCSQLException(final String message, final Throwable cause) {
		super(message, cause);
	}


	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TFCSQLException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}


}
