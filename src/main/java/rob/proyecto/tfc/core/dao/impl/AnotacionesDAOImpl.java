package rob.proyecto.tfc.core.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import rob.proyecto.tfc.TFCConstantes.TIPO_SANITARIO;
import rob.proyecto.tfc.core.dao.AnotacionesDAO;
import rob.proyecto.tfc.data.entity.Anotacion;


/**
 * DAO para la gestion de la entidad "Anotacion".
 * 
 * @author user
 * 
 */
@Repository("AnotacionesDAO")
public class AnotacionesDAOImpl extends AbstractDAOImpl<Anotacion> implements AnotacionesDAO
{

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarAll()
	 */
	@Override
	public List<Anotacion> buscarAll()
	{
		Query query = getEm().createNamedQuery("Anotacion.findAll");

		@SuppressWarnings("unchecked")
		List<Anotacion> lista = query.getResultList();
		return lista;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarByExample(java.lang.Object)
	 */
	@Override
	public List<Anotacion> buscarByExample(final Anotacion entidad)
	{
		return null;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AnotacionesDAO#buscarNotasPacientePorEditor(java.lang.Long, rob.proyecto.tfc.core.TFCConstantes.TIPO_SANITARIO)
	 */
	@Override
	public List<Anotacion> buscarNotasPacientePorEditor(final Long idPaciente, final TIPO_SANITARIO tipoSanitario)
	{
		String sql = "SELECT a FROM Anotacion a, Sanitario s";
		sql += " WHERE a.id.idPaciente=:idPac";
		sql += " AND a.id.idSanitario=s.id";
		sql += " AND s.categoria=:tipoSanitario";
		sql += " order by a.id.fecha desc";

		Query query = getEm().createQuery(sql);
		query.setParameter("idPac", idPaciente);
		query.setParameter("tipoSanitario", tipoSanitario.toString());

		@SuppressWarnings("unchecked")
		List<Anotacion> lista = query.getResultList();
		return lista;
	}


}
