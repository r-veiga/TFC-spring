package rob.proyecto.tfc.core.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.TFCConstantes.TIPO_SANITARIO;
import rob.proyecto.tfc.core.dao.AnotacionesDAO;
import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.AnotacionesService;
import rob.proyecto.tfc.data.entity.Anotacion;


/**
 * Servicio para la logica de "Anotaciones".
 * 
 * @author user
 * 
 */
@Service("AnotacionesService")
public class AnotacionesSrvImpl implements AnotacionesService
{

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(AnotacionesSrvImpl.class);


	/**
	 * DAO de acceso a los datos de la entidad "Anotacion".
	 */
	@Autowired
	@Qualifier("AnotacionesDAO")
	private AnotacionesDAO anotacionesDAO;


	/**
	 * @param anotacionesDAO the anotacionesDAO to set
	 */
	public void setAnotacionesDAO(final AnotacionesDAO anotacionesDAO)
	{
		this.anotacionesDAO = anotacionesDAO;
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.AnotacionesService#buscarNotasPacientePorEditor(java.lang.Long, rob.proyecto.tfc.core.TFCConstantes.TIPO_SANITARIO)
	 */
	@Override
	public List<Anotacion> buscarNotasPacientePorEditor(final Long idPaciente, final TIPO_SANITARIO tipoSanitario)
	{
		return anotacionesDAO.buscarNotasPacientePorEditor(idPaciente, tipoSanitario);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.AnotacionesService#crearAnotacion(rob.proyecto.tfc.data.entity.Anotacion)
	 */
	@Override
	@Transactional
	public Anotacion crearAnotacion(final Anotacion anotacion) throws TFCException
	{
		LOG.info("Creando nuevo registro");
		return anotacionesDAO.crear(anotacion);
	}


}
