package rob.proyecto.tfc.core.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.PacientesDAO;
import rob.proyecto.tfc.data.entity.Paciente;


/**
 * DAO para la gestion de la entidad "Paciente".
 * 
 * @author user
 * 
 */
@Repository("PacientesDAO")
public class PacientesDAOImpl extends AbstractDAOImpl<Paciente> implements PacientesDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Paciente> buscarAll()
	{
		Query query = getEm().createNamedQuery("Paciente.findAll");

		@SuppressWarnings("unchecked")
		List<Paciente> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Paciente> buscarByExample(final Paciente entidad)
	{
		String sql = "SELECT p FROM Paciente p";

		if(null != entidad)
		{
			sql = sqlWriter(sql, "p.id", entidad.getId());
			sql = sqlWriter(sql, "p.expediente", entidad.getExpediente());
			sql = sqlWriter(sql, "p.dni", entidad.getDni());

			if(null != entidad.isHospitalizado())
			{
				if(entidad.isHospitalizado())
				{
					sql = sqlWriter(sql, "p.fecIngreso <> NULL");
				}
				else
				{
					sql = sqlWriter(sql, "p.fecIngreso IS NULL");
				}
			}
		}

		sql += " ORDER BY p.id";

		Query query = getEm().createQuery(sql);

		@SuppressWarnings("unchecked")
		List<Paciente> lista = query.getResultList();

		return lista;
	}


}
