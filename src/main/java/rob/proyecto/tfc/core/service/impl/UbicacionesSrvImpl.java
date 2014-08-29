package rob.proyecto.tfc.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import rob.proyecto.tfc.core.dao.UbicacionesDAO;
import rob.proyecto.tfc.core.service.UbicacionesService;
import rob.proyecto.tfc.data.entity.Ubicacion;


/**
 * Servicio para la logica de "Ubicaciones".
 * 
 * @author user
 * 
 */
@Service("UbicacionesService")
public class UbicacionesSrvImpl implements UbicacionesService
{

	/**
	 * DAO de acceso a los datos de la entidad "Ubicacion".
	 */
	@Autowired
	@Qualifier("UbicacionesDAO")
	private UbicacionesDAO ubicacionesDAO;


	/**
	 * @param ubicacionesDAO the ubicacionesDAO to set
	 */
	public void setUbicacionesDAO(final UbicacionesDAO ubicacionesDAO)
	{
		this.ubicacionesDAO = ubicacionesDAO;
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.UbicacionesService#buscarTodas()
	 */
	@Override
	public List<Ubicacion> buscarTodas()
	{
		return ubicacionesDAO.buscarAll();
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.UbicacionesService#buscarTodasLibres()
	 */
	@Override
	public List<Ubicacion> buscarTodasLibres()
	{
		return ubicacionesDAO.buscarTodasLibres();
	}


}
