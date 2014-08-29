package rob.proyecto.tfc.core.dao;


import java.util.Date;
import java.util.List;

import rob.proyecto.tfc.data.entity.Cita;


/**
 * Interfaz de metodos de acceso a datos de la entidad "Cita".
 * 
 * @author user
 * 
 */
public interface CitasDAO extends AbstractDAO<Cita>
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


}
