package rob.proyecto.tfc.core.dao;


import java.util.List;

import rob.proyecto.tfc.core.exception.TFCSQLException;


/**
 * Interfaz comun para la definicion de metodos CRUD.
 * 
 * @author user
 * 
 * @param <T>
 */
public interface AbstractDAO<T>
{

	/**
	 * Metodo para BUSCAR todas las entidades.
	 * 
	 * @param entidad
	 * @return
	 */
	abstract List<T> buscarAll();


	/**
	 * Metodo para BUSCAR una entidad por PK.
	 * 
	 * @param entidad
	 * @param objPK
	 * @return
	 */
	abstract T buscarPK(T entidad, Object objPK);


	/**
	 * Metodo para BUSCAR todas las entidades similares a la entidad T.
	 * 
	 * @param entidad
	 * @return
	 */
	abstract List<T> buscarByExample(T entidad);



	/**
	 * Metodo para CREAR una entidad.
	 * 
	 * @param entidad
	 * @return
	 * @throws TFCSQLException
	 */
	abstract T crear(T entidad) throws TFCSQLException;


	/**
	 * Metodo para BORRAR una entidad.
	 * 
	 * @param entidad
	 * @return
	 * @throws TFCSQLException
	 */
	abstract T borrar(T entidad) throws TFCSQLException;


	/**
	 * Metodo para MODIFICAR una entidad.
	 * 
	 * @param entidad
	 * @return
	 * @throws TFCSQLException
	 */
	abstract T modificar(T entidad) throws TFCSQLException;

}
