package rob.proyecto.tfc.core.service;


import java.util.List;

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.data.entity.Nota;
import rob.proyecto.tfc.data.entity.Sanitario;


/**
 * Intefaz de metodos de logica de "Sanitarios".
 * 
 * @author user
 * 
 */
public interface SanitariosService
{

	//-- Metodos de gestion de Sanitario

	/**
	 * Metodo para buscar un sanitario por su identificador.
	 * 
	 * @param idSanitario
	 * @return
	 */
	Sanitario buscarSanitario(Long idSanitario);


	/**
	 * Metodo para buscar sanitarios que coincidan con los datos indicados.
	 * 
	 * @param paciente
	 * @return
	 */
	List<Sanitario> buscarSanitarios(Sanitario sanitario);


	/**
	 * Metodo para crear un sanitario.
	 * 
	 * @param sanitario
	 * @return
	 * @throws TFCException
	 */
	Sanitario crearSanitario(Sanitario sanitario) throws TFCException;


	/**
	 * Metodo para modificar un sanitario.
	 * 
	 * @param sanitario
	 * @return
	 * @throws TFCException
	 */
	Sanitario modificarSanitario(Sanitario sanitario) throws TFCException;



	//-- Metodos de gestion de Notas de sanitario.

	/**
	 * Metodo para bucarr todas las notas de un sanitario.
	 * 
	 * @param idSanitario
	 * @return
	 */
	List<Nota> buscarNotasSanitario(Long idSanitario);


	/**
	 * Metodo para crear una nota de un sanitario.
	 * 
	 * @param nota
	 * @return
	 * @throws TFCException
	 */
	Nota crearNota(Nota nota) throws TFCException;


	/**
	 * Metodo para crear una nota de un sanitario.
	 * 
	 * @param nota
	 * @return
	 * @throws TFCException
	 */
	Nota borrarNota(Nota nota) throws TFCException;



}
