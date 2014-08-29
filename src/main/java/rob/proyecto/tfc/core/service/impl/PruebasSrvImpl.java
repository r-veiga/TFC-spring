package rob.proyecto.tfc.core.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.core.dao.PruebasDAO;
import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.PruebasService;
import rob.proyecto.tfc.data.entity.Prueba;
import rob.proyecto.tfc.data.entity.PruebaPK;


/**
 * Servicio para la logica de "Pruebas".
 * 
 * @author user
 * 
 */
@Service("PruebasService")
public class PruebasSrvImpl implements PruebasService
{

	/**
	 * DAO de acceso a los datos de la entidad "Prueba".
	 */
	@Autowired
	@Qualifier("PruebasDAO")
	private PruebasDAO pruebasDAO;


	/**
	 * @param pruebasDAO the pruebasDAO to set
	 */
	public void setPruebasDAO(final PruebasDAO pruebasDAO)
	{
		this.pruebasDAO = pruebasDAO;
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.PruebasService#buscarPruebasdePaciente(java.lang.Long)
	 */
	@Override
	public List<Prueba> buscarPruebasdePaciente(final Long idPaciente)
	{
		PruebaPK pk = new PruebaPK();
		pk.setIdPaciente(idPaciente);

		Prueba prueba = new Prueba();
		prueba.setId(pk);

		return pruebasDAO.buscarByExample(prueba);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.PruebasService#crearPrueba(rob.proyecto.tfc.data.entity.Prueba)
	 */
	@Override
	@Transactional
	public Prueba crearPrueba(final Prueba prueba) throws TFCException
	{
		if(null == prueba.getId().getFecAlta())
		{
			prueba.getId().setFecAlta(new Date());
		}

		return pruebasDAO.crear(prueba);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.PruebasService#borrarPrueba(rob.proyecto.tfc.data.entity.Prueba)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Prueba borrarPrueba(final Prueba prueba) throws TFCException
	{
		Prueba pruebaBorrar = pruebasDAO.buscarPK(prueba, prueba.getId());

		return pruebasDAO.borrar(pruebaBorrar);
	}


}
