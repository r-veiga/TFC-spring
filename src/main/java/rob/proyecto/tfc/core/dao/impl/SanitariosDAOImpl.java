package rob.proyecto.tfc.core.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.SanitariosDAO;
import rob.proyecto.tfc.data.entity.Sanitario;


/**
 * DAO para la gestion de la entidad "Sanitario".
 * 
 * @author user
 * 
 */
@Repository("SanitariosDAO")
public class SanitariosDAOImpl extends AbstractDAOImpl<Sanitario> implements SanitariosDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Sanitario> buscarAll()
	{
		Query query = getEm().createNamedQuery("Sanitario.findAll");

		@SuppressWarnings("unchecked")
		List<Sanitario> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Sanitario> buscarByExample(final Sanitario entidad)
	{
		String sql = "SELECT s FROM Sanitario s";

		if(null != entidad)
		{
			sql = sqlWriter(sql, "s.colegiado", entidad.getColegiado());
			sql = sqlWriter(sql, "s.nombre", entidad.getNombre());
			sql = sqlWriter(sql, "s.apellidos", entidad.getApellidos());
			sql = sqlWriter(sql, "s.idEspecialidad", entidad.getIdEspecialidad());
		}

		sql += " ORDER BY s.id";

		Query query = getEm().createQuery(sql);

		@SuppressWarnings("unchecked")
		List<Sanitario> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.SanitariosDAO#buscarSanitariosSinUsuario()
	 */
	@Override
	public List<Sanitario> buscarSanitariosSinUsuario()
	{
		Query query = getEm().createNamedQuery("Sanitario.findAllNoUser");

		@SuppressWarnings("unchecked")
		List<Sanitario> lista = query.getResultList();
		return lista;
	}

}
