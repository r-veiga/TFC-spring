package rob.proyecto.tfc.core.dao.impl;


import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.UsuariosDAO;
import rob.proyecto.tfc.core.exception.TFCSQLException;
import rob.proyecto.tfc.data.entity.Usuario;


/**
 * DAO para la gestion de la entidad "Usuario".
 * 
 * @author user
 * 
 */
@Repository("UsuariosDAO")
public class UsuariosDAOImpl extends AbstractDAOImpl<Usuario> implements UsuariosDAO
{

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(UsuariosDAOImpl.class);


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Usuario> buscarAll()
	{
		Query query = getEm().createNamedQuery("Usuario.findAll");

		@SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Usuario> buscarByExample(final Usuario entidad)
	{
		String sql = "SELECT u FROM Usuario u";

		if(null != entidad)
		{
			sql = sqlWriter(sql, "u.nombre", entidad.getNombre());
			sql = sqlWriter(sql, "u.apellidos", entidad.getApellidos());
			sql = sqlWriter(sql, "u.tipo", entidad.getTipo());
		}

		sql += " ORDER BY u.id";

		Query query = getEm().createQuery(sql);

		@SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.UsuariosDAO#buscarAcceso(rob.proyecto.tfc.data.entity.Usuario)
	 */
	@Override
	public Usuario buscarAcceso(final Usuario entidad) throws TFCSQLException
	{
		Query query = getEm().createNamedQuery("Usuario.findBy_UsrPas");
		query.setParameter("user", entidad.getUser());
		query.setParameter("pass", entidad.getPass());

		Usuario usuario = null;

		try
		{
			usuario = (Usuario)query.getSingleResult();
		}
		catch(NoResultException e)
		{
			LOG.error("Intento de acceso con Usuario : ''" + entidad.getUser() + "'/'" + entidad.getPass() + "'.", e);
		}

		if(usuario != null)
		{
			usuario.setFecAcceso(new Date());
			usuario = modificar(usuario);
		}

		return usuario;
	}


}
