package rob.proyecto.tfc.core.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.EspecialidadesDAO;
import rob.proyecto.tfc.data.entity.Especialidad;


/**
 * DAO para la gestion de la entidad "Especialidad".
 * 
 * @author user
 * 
 */
@Repository("EspecialidadesDAO")
public class EspecialidadesDAOImpl extends AbstractDAOImpl<Especialidad> implements EspecialidadesDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Especialidad> buscarAll()
	{
		Query query = getEm().createNamedQuery("Especialidad.findAll");

		@SuppressWarnings("unchecked")
		List<Especialidad> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Especialidad> buscarByExample(final Especialidad entidad)
	{
		return null;
	}


}
