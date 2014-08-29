package rob.proyecto.tfc.core.dao;


import java.util.List;

import rob.proyecto.tfc.data.entity.Ubicacion;


/**
 * Interfaz de metodos de acceso a datos de la entidad "Ubicacion".
 * 
 * @author user
 * 
 */
public interface UbicacionesDAO extends AbstractDAO<Ubicacion>
{

	/**
	 * Metodo para obtener solo las ubicaciones libres.
	 * 
	 * @return
	 */
	List<Ubicacion> buscarTodasLibres();

}
