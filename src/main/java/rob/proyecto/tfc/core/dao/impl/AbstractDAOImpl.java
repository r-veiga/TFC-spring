package rob.proyecto.tfc.core.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import rob.proyecto.tfc.core.exception.TFCSQLException;


/**
 * DAO Abstracto con los metodos CRUD para todas las entidades
 * 
 * @author user
 * 
 */
public abstract class AbstractDAOImpl<T>
{

	/** Contexto de persistencia JPA inyectado por Spring. */
	@PersistenceContext
	private EntityManager em;


	/**
	 * @return the em
	 */
	public EntityManager getEm()
	{
		return em;
	}


	/**
	 * @param em the em to set
	 */
	public void setEm(final EntityManager em)
	{
		this.em = em;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#buscarPK(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public T buscarPK(final T entidad, final Object objPK)
	{
		return (T)getEm().find(entidad.getClass(), objPK);
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#crear(java.lang.Object)
	 */
	public T crear(final T entidad) throws TFCSQLException
	{
		System.err.println("--- crear ---");

		try
		{
			em.persist(entidad);
			em.flush();
		}
		catch(Exception e)
		{
			throw new TFCSQLException(e);
		}

		return entidad;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#borrar(java.lang.Object)
	 */
	public T borrar(final T entidad) throws TFCSQLException
	{
		System.err.println("--- borrar ---");

		try
		{
			em.remove(entidad);
			em.flush();
		}
		catch(Exception e)
		{
			throw new TFCSQLException(e);
		}

		return entidad;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.dao.AbstractDAO#modificar(java.lang.Object)
	 */
	public T modificar(final T entidad) throws TFCSQLException
	{
		System.err.println("--- modificar ---");

		try
		{
			em.merge(entidad);
		}
		catch(Exception e)
		{
			throw new TFCSQLException(e);
		}

		return entidad;
	}



	/**
	 * Metodo de ayuda a la construccion de sentencias SQL, creando la clausula WHERE.
	 * 
	 * @param sqlOriginal
	 * @param campo
	 * @param valor
	 * @return
	 */
	protected final String sqlWriter(final String sqlOriginal, final String campo, final Object valor)
	{
		StringBuffer strBuff = new StringBuffer(sqlOriginal);

		if(valor != null)
		{
			if(sqlOriginal.contains("WHERE"))
			{
				strBuff.append(" AND ");
			}
			else
			{
				strBuff.append(" WHERE ");
			}

			if(String.class == valor.getClass())
			{
				strBuff.append(campo).append(" LIKE '").append(valor).append("'");
			}
			else
			{
				strBuff.append(campo).append(" = ").append(valor);
			}
		}

		return strBuff.toString();
	}



	/**
	 * Metodo de ayuda a la construccion de sentencias SQL, creando la clausula WHERE.
	 * 
	 * @param sqlOriginal
	 * @param condicion (campo + comparacion + valor)
	 * @return
	 */
	protected final String sqlWriter(final String sqlOriginal, final String condicion)
	{
		StringBuffer strBuff = new StringBuffer(sqlOriginal);

		if(condicion != null)
		{
			if(sqlOriginal.contains("WHERE"))
			{
				strBuff.append(" AND ");
			}
			else
			{
				strBuff.append(" WHERE ");
			}

			strBuff.append(condicion);
		}

		return strBuff.toString();
	}


}
