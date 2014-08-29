package rob.proyecto.tfc.core.service;


import java.util.List;

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.data.entity.Prueba;


/**
 * Intefaz de metodos de logica de "Pruebas".
 * 
 * @author user
 * 
 */
public interface PruebasService
{

	/**
	 * Metodo para obtener las pruebas de un paciente por su id.
	 * 
	 * @param idPaciente
	 * @return
	 */
	List<Prueba> buscarPruebasdePaciente(Long idPaciente);


	/**
	 * Metodo para crear una prueba de un paciente.
	 * 
	 * @param prueba
	 * @return
	 * @throws TFCException
	 */
	Prueba crearPrueba(Prueba prueba) throws TFCException;


	/**
	 * Metodo para borrar una prueba de un paciente.
	 * 
	 * @param prueba
	 * @return
	 * @throws TFCException
	 */
	Prueba borrarPrueba(Prueba prueba) throws TFCException;


}
