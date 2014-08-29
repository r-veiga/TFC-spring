package rob.proyecto.tfc.core.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.core.dao.CategoriasDAO;
import rob.proyecto.tfc.data.entity.Categoria;


/**
 * DAO para la gestion de la entidad "Categoria".
 * 
 * @author user
 * 
 */
@Repository("CategoriasDAO")
public class CategoriasDAOImpl extends AbstractDAOImpl<Categoria> implements CategoriasDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Categoria> buscarAll()
	{
		Query query = getEm().createNamedQuery("Categoria.findAll");

		@SuppressWarnings("unchecked")
		List<Categoria> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Categoria> buscarByExample(final Categoria entidad)
	{
		return null;
	}


}
