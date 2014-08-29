package rob.proyecto.tfc.core.dao;


import java.util.List;

import rob.proyecto.tfc.data.entity.Sanitario;


/**
 * Interfaz de metodos de acceso a datos de la entidad "Sanitario".
 * 
 * @author user
 * 
 */
public interface SanitariosDAO extends AbstractDAO<Sanitario>
{


	/**
	 * Metodo para buscar el personal sanitario sin usuario de la aplicacion.
	 * 
	 * @return
	 */
	List<Sanitario> buscarSanitariosSinUsuario();


}
