package rob.proyecto.tfc.core.service;


import java.util.List;

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.data.entity.Paciente;


/**
 * Intefaz de metodos de logica de "Pacientes".
 * 
 * @author user
 * 
 */
public interface PacientesService
{

	/**
	 * Metodo para buscar un paciente por su identificador.
	 * 
	 * @param idPaciente
	 * @return
	 */
	Paciente buscarPaciente(Long idPaciente);


	/**
	 * Metodo para buscar pacientes que coincidan con los datos indicados.
	 * 
	 * @param paciente
	 * @return
	 */
	List<Paciente> buscarPacientes(Paciente paciente);


	/**
	 * Metodo para crear un paciente.
	 * 
	 * @param paciente
	 * @return
	 * @throws TFCException
	 */
	Paciente crearPaciente(Paciente paciente) throws TFCException;


	/**
	 * Metodo para modificar un paciente.
	 * 
	 * @param paciente
	 * @return
	 * @throws TFCException
	 */
	Paciente modificarPaciente(Paciente paciente) throws TFCException;


}
