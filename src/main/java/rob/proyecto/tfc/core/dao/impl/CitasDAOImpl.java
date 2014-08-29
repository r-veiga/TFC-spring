package rob.proyecto.tfc.core.dao.impl;


import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.CitasDAO;
import rob.proyecto.tfc.data.entity.Cita;


/**
 * DAO para la gestion de la entidad "Cita".
 * 
 * @author user
 * 
 */
@Repository("CitasDAO")
public class CitasDAOImpl extends AbstractDAOImpl<Cita> implements CitasDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Cita> buscarAll()
	{
		Query query = getEm().createNamedQuery("Cita.findAll");

		@SuppressWarnings("unchecked")
		List<Cita> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Cita> buscarByExample(final Cita entidad)
	{
		return null;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.CitasDAO#buscarCitasEspecialidad(java.lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Cita> buscarCitasEspecialidad(final Long idEspecialidad, final Date fechaDesde, final Date fechaHasta)
	{
		String sql = "SELECT c FROM Cita c WHERE c.id.idEspecialidad = :id";

		if(fechaDesde != null)
		{
			sql += " AND c.id.fecha >= :fDesde";
		}
		if(fechaHasta != null)
		{
			sql += " AND c.id.fecha < :fHasta";
		}

		sql += " ORDER BY c.id";


		Query query = getEm().createQuery(sql);

		query.setParameter("id", idEspecialidad);
		if(fechaDesde != null)
		{
			query.setParameter("fDesde", fechaDesde, TemporalType.TIMESTAMP);
		}
		if(fechaHasta != null)
		{
			query.setParameter("fHasta", fechaHasta, TemporalType.TIMESTAMP);
		}


		@SuppressWarnings("unchecked")
		List<Cita> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.CitasDAO#buscarCitasPaciente(java.lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Cita> buscarCitasPaciente(final Long idPaciente, final Date fechaDesde, final Date fechaHasta)
	{
		String sql = "SELECT c FROM Cita c WHERE c.id.idPaciente = :id";

		if(fechaDesde != null)
		{
			sql += " AND c.id.fecha >= :fDesde";
		}
		if(fechaHasta != null)
		{
			sql += " AND c.id.fecha < :fHasta";
		}

		sql += " ORDER BY c.id";


		Query query = getEm().createQuery(sql);

		query.setParameter("id", idPaciente);
		if(fechaDesde != null)
		{
			query.setParameter("fDesde", fechaDesde, TemporalType.TIMESTAMP);
		}
		if(fechaHasta != null)
		{
			query.setParameter("fHasta", fechaHasta, TemporalType.TIMESTAMP);
		}


		@SuppressWarnings("unchecked")
		List<Cita> lista = query.getResultList();
		return lista;
	}
}
