package rob.proyecto.tfc.core.dao;


import rob.proyecto.tfc.core.exception.TFCSQLException;
import rob.proyecto.tfc.data.entity.Usuario;


/**
 * Interfaz de metodos de acceso a datos de la entidad "Usuario".
 * 
 * @author user
 * 
 */
public interface UsuariosDAO extends AbstractDAO<Usuario>
{

	/**
	 * Metodo para la validacion de acceso de un usuario.
	 * 
	 * @param entidad
	 * @return
	 * @throws TFCSQLException
	 */
	Usuario buscarAcceso(Usuario entidad) throws TFCSQLException;


}
