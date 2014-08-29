package rob.proyecto.tfc;


/**
 * Clase para la definicion de constantes de logica de la aplicacion.
 * 
 * @author user
 * 
 */
public final class TFCConstantes
{

	/**
	 * Valor asociado a la clave definida en el archivo web.xml para poder configurar la ruta
	 * donde esta el repositorio de archivos adjuntos de las pruebas.
	 */
	public static String TFC_RUTA_ARCHIVOS_PRUEBAS = "";


	/**
	 * Tipos de sanitarios. Se utiliza para definir las tabs de anotacioness .
	 */
	public static enum TIPO_SANITARIO {
		MEDICO, ENFERMERIA, AUXILIAR, REHABILITACION, TRABAJO_SOCIAL
	}


	/**
	 * Cte. con el valor en ms. de 1 minuto.
	 */
	public static final long MILISEC_MINUTO = 60 * 1000;

	/**
	 * Cte. con el valor en ms. de 1 hora.
	 */
	public static final long MILISEC_HORA = 60 * MILISEC_MINUTO;


	/**
	 * Hora (en ms.) de inicio del horario de consultas.
	 */
	public static final long CONSULTAS_HORA_INICIO = 8 * MILISEC_HORA;

	/**
	 * Hora (en ms.) de fin del horario de consultas.
	 */
	public static final long CONSULTAS_HORA_FIN = 20 * MILISEC_HORA;

	/**
	 * Duracion (en ms.) del tiempo de un aconsulta (30 mins).
	 */
	public static final long CUNSULTA_DURACION = 30 * MILISEC_MINUTO;



	/** Contructor privado para evitar la creacion de objetos de esta clase. */
	private TFCConstantes() {}

}
