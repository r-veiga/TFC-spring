package rob.proyecto.tfc.core.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.UbicacionesDAO;
import rob.proyecto.tfc.data.entity.Ubicacion;


/**
 * DAO para la gestion de la entidad "Ubicacion".
 * 
 * @author user
 * 
 */
@Repository("UbicacionesDAO")
public class UbicacionesDAOImpl extends AbstractDAOImpl<Ubicacion> implements UbicacionesDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Ubicacion> buscarAll()
	{
		Query query = getEm().createNamedQuery("Ubicacion.findAll");

		@SuppressWarnings("unchecked")
		List<Ubicacion> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Ubicacion> buscarByExample(final Ubicacion entidad)
	{
		return null;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.UbicacionesDAO#buscarTodasLibres()
	 */
	@Override
	public List<Ubicacion> buscarTodasLibres()
	{
		Query query = getEm().createNamedQuery("Ubicacion.findAllVacancy");

		@SuppressWarnings("unchecked")
		List<Ubicacion> lista = query.getResultList();
		return lista;
	}


}
