package rob.proyecto.tfc.core.dao.impl;


import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.NotasDAO;
import rob.proyecto.tfc.data.entity.Nota;


/**
 * DAO para la gestion de la entidad "Nota".
 * 
 * @author user
 * 
 */
@Repository("NotasDAO")
public class NotasDAOImpl extends AbstractDAOImpl<Nota> implements NotasDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Nota> buscarAll()
	{
		Query query = getEm().createNamedQuery("Nota.findAll");

		@SuppressWarnings("unchecked")
		List<Nota> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Nota> buscarByExample(final Nota entidad)
	{
		Date fecha = new Date(1L);

		if(null != entidad.getId().getFecha())
		{
			fecha = entidad.getId().getFecha();
		}

		String sql = "SELECT n FROM Nota n WHERE n.id.idSanitario = :id AND n.id.fecha >= :fecha ORDER BY n.id.fecha";

		Query query = getEm().createQuery(sql);
		query.setParameter("id", entidad.getId().getIdSanitario());
		query.setParameter("fecha", fecha, TemporalType.TIMESTAMP);

		@SuppressWarnings("unchecked")
		List<Nota> lista = query.getResultList();
		return lista;
	}


}
