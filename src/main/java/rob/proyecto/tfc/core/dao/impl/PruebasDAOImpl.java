package rob.proyecto.tfc.core.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.PruebasDAO;
import rob.proyecto.tfc.data.entity.Prueba;


/**
 * DAO para la gestion de la entidad "Prueba".
 * 
 * @author user
 * 
 */
@Repository("PruebasDAO")
public class PruebasDAOImpl extends AbstractDAOImpl<Prueba> implements PruebasDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Prueba> buscarAll()
	{
		Query query = getEm().createNamedQuery("Prueba.findAll");

		@SuppressWarnings("unchecked")
		List<Prueba> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Prueba> buscarByExample(final Prueba entidad)
	{
		String sql = "SELECT p FROM Prueba p";

		if(null != entidad)
		{
			sql = sqlWriter(sql, "p.id.idPaciente", entidad.getId().getIdPaciente());
			sql = sqlWriter(sql, "p.id.fecAlta", entidad.getId().getFecAlta());
		}

		sql += " ORDER BY p.id";

		Query query = getEm().createQuery(sql);

		@SuppressWarnings("unchecked")
		List<Prueba> lista = query.getResultList();
		return lista;
	}


}
