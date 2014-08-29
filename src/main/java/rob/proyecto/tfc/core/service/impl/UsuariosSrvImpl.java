package rob.proyecto.tfc.core.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.core.dao.SanitariosDAO;
import rob.proyecto.tfc.core.dao.UsuariosDAO;
import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.UsuariosService;
import rob.proyecto.tfc.data.entity.Sanitario;
import rob.proyecto.tfc.data.entity.Usuario;
import rob.proyecto.tfc.data.vo.UsuarioVO;


/**
 * Servicio para la logica de "Usuarios".
 * 
 * @author user
 * 
 */
@Service("UsuariosService")
public class UsuariosSrvImpl implements UsuariosService
{

	/**
	 * DAO de acceso a los datos de la entidad "Usuario".
	 */
	@Autowired
	@Qualifier("UsuariosDAO")
	private UsuariosDAO usuariosDAO;

	/**
	 * DAO de acceso a los datos de la entidad "Sanitario".
	 */
	@Autowired
	@Qualifier("SanitariosDAO")
	private SanitariosDAO sanitariosDAO;


	/**
	 * @param usuariosDAO the usuariosDAO to set
	 */
	public void setUsuariosDAO(final UsuariosDAO usuariosDAO)
	{
		this.usuariosDAO = usuariosDAO;
	}

	/**
	 * @param sanitariosDAO the sanitariosDAO to set
	 */
	public void setSanitariosDAO(final SanitariosDAO sanitariosDAO)
	{
		this.sanitariosDAO = sanitariosDAO;
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.UsuariosService#validarUsuario(rob.proyecto.tfc.data.entity.Usuario)
	 */
	@Override
	@Transactional
	public UsuarioVO validarUsuario(final Usuario usuario) throws TFCException
	{
		Usuario usrDB = usuariosDAO.buscarAcceso(usuario);

		UsuarioVO user = null;

		if(usrDB != null)
		{
			user = new UsuarioVO();

			user.setIdUsuario(usrDB.getId());
			user.setTipo(usrDB.getTipo());
			user.setNombre(usrDB.getNombre() + " " + usrDB.getApellidos());
			user.setUltimoAcceso(usrDB.getFecAcceso());

			if(usrDB.getSanitario() != null)
			{
				user.setIdSanitario(usrDB.getSanitario().getId());
				user.setCategoria(usrDB.getSanitario().getCategoria());
				user.setIdEspecialidad(usrDB.getSanitario().getIdEspecialidad());
			}
		}

		return user;
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.UsuariosService#buscarUsuario(java.lang.Long)
	 */
	@Override
	public Usuario buscarUsuario(final Long idUsuario)
	{
		return usuariosDAO.buscarPK(new Usuario(), idUsuario);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.UsuariosService#buscarUsuarios(rob.proyecto.tfc.data.entity.Usuario)
	 */
	@Override
	public List<Usuario> buscarUsuarios(final Usuario usuario)
	{
		if(null == usuario)
		{
			return usuariosDAO.buscarAll();
		}
		else
		{
			return usuariosDAO.buscarByExample(usuario);
		}
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.UsuariosService#buscarSanitariosSinUsuario()
	 */
	@Override
	public List<Sanitario> buscarSanitariosSinUsuario()
	{
		return sanitariosDAO.buscarSanitariosSinUsuario();
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.UsuariosService#crearUsuario(rob.proyecto.tfc.data.entity.Usuario)
	 */
	@Override
	@Transactional
	public Usuario crearUsuario(final Usuario usuario) throws TFCException
	{
		usuario.setFecAlta(new Date());

		return usuariosDAO.crear(usuario);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.UsuariosService#modificarUsuario(rob.proyecto.tfc.data.entity.Usuario)
	 */
	@Override
	@Transactional
	public Usuario modificarUsuario(final Usuario usuario) throws TFCException
	{
		return usuariosDAO.modificar(usuario);
	}


}
