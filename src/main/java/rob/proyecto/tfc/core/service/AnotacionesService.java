package rob.proyecto.tfc.core.service;


import java.util.List;

import rob.proyecto.tfc.TFCConstantes;
import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.data.entity.Anotacion;


/**
 * Intefaz de metodos de logica de "Anotaciones".
 * 
 * @author user
 * 
 */
public interface AnotacionesService
{

	/**
	 * Metodo para obtener solo las anotaciones de un tipo de sanitario para un paciente.
	 * 
	 * @param idPaciente
	 * @param tipoSanitario
	 * @return
	 */
	List<Anotacion> buscarNotasPacientePorEditor(Long idPaciente, TFCConstantes.TIPO_SANITARIO tipoSanitario);


	/**
	 * Metodo para crear un anueva Anotacion.
	 * 
	 * @param anotacion
	 * @return
	 * @throws TFCException
	 */
	Anotacion crearAnotacion(Anotacion anotacion) throws TFCException;

}
