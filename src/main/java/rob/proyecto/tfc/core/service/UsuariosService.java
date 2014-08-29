package rob.proyecto.tfc.core.service;


import java.util.List;

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.data.entity.Sanitario;
import rob.proyecto.tfc.data.entity.Usuario;
import rob.proyecto.tfc.data.vo.UsuarioVO;


/**
 * Intefaz de metodos de logica de "Usuarios".
 * 
 * @author user
 * 
 */
public interface UsuariosService
{

	/**
	 * Metodo para validar un usuario por USR/PASS.
	 * 
	 * @param usuario
	 * @return
	 * @throws TFCException
	 */
	UsuarioVO validarUsuario(Usuario usuario) throws TFCException;


	/**
	 * Metodo para buscar un Usuarios por su identificador.
	 * 
	 * @param idUsuario
	 * @return
	 */
	Usuario buscarUsuario(Long idUsuario);


	/**
	 * Metodo para buscar usuarios que coincidan con los datos indicados.
	 * 
	 * @param usuario
	 * @return
	 */
	List<Usuario> buscarUsuarios(Usuario usuario);


	/**
	 * Metodo para buscar el personal sanitario sin usuario de la aplicacion.
	 * 
	 * @return
	 */
	List<Sanitario> buscarSanitariosSinUsuario();


	/**
	 * Metodo para crear un usuario.
	 * 
	 * @param usuario
	 * @return
	 * @throws TFCException
	 */
	Usuario crearUsuario(Usuario usuario) throws TFCException;


	/**
	 * Metodo para modificar un usuario.
	 * 
	 * @param usuario
	 * @return
	 * @throws TFCException
	 */
	Usuario modificarUsuario(Usuario usuario) throws TFCException;


}
