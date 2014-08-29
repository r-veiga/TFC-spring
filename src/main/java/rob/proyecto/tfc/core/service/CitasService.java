package rob.proyecto.tfc.core.service;


import java.util.Date;
import java.util.List;

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.data.entity.Cita;
import rob.proyecto.tfc.data.vo.ConsultaHoraVO;


/**
 * Intefaz de metodos de logica de "Citas".
 * 
 * @author user
 * 
 */
public interface CitasService
{

	/**
	 * Metodo para obtener las citas de una especialidad por su id, entre dos fechas indicadas.
	 * Si las fechas son NULL, no se tendran en cuenta para la busqueda.
	 * 
	 * @param idEspecialidad
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	List<Cita> buscarCitasEspecialidad(Long idEspecialidad, Date fechaDesde, Date fechaHasta);


	/**
	 * Metodo para obtener las citas de un padiente por su id, entre dos fechas indicadas.
	 * Si las fechas son NULL, no se tendran en cuenta para la busqueda.
	 * 
	 * @param idPaciente
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	List<Cita> buscarCitasPaciente(Long idPaciente, Date fechaDesde, Date fechaHasta);


	/**
	 * Metodo para obtener la lista de consultas de una especialidad para un dia.
	 * Devuelve todo el horario y la cita asociada a la hora si aplica.
	 * 
	 * @param idEspecialidad
	 * @param fecha
	 * @return
	 */
	List<ConsultaHoraVO> buscarHorasConsultaEspecialidad(Long idEspecialidad, Date fecha);


	/**
	 * Metodo para crear una cita de un paciente.
	 * 
	 * @param cita
	 * @return
	 * @throws TFCException
	 */
	Cita crearCita(Cita cita) throws TFCException;


	/**
	 * Metodo para borrar una prueba de un paciente.
	 * 
	 * @param cita
	 * @return
	 * @throws TFCException
	 */
	Cita borrarCita(Cita cita) throws TFCException;


}
