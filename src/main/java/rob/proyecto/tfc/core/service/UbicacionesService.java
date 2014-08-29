package rob.proyecto.tfc.core.service;


import java.util.List;

import rob.proyecto.tfc.data.entity.Ubicacion;


/**
 * Intefaz de metodos de logica de "Ubicacion".
 * 
 * @author user
 * 
 */
public interface UbicacionesService
{

	/**
	 * Metodo para obtener todas las ubicaciones.
	 * 
	 * @return
	 */
	List<Ubicacion> buscarTodas();

	/**
	 * Metodo para obtener solo las ubicaciones libres.
	 * 
	 * @return
	 */
	List<Ubicacion> buscarTodasLibres();

}
