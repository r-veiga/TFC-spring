package rob.proyecto.tfc.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import rob.proyecto.tfc.core.dao.CategoriasDAO;
import rob.proyecto.tfc.core.dao.EspecialidadesDAO;
import rob.proyecto.tfc.core.service.MaestrosService;
import rob.proyecto.tfc.data.entity.Categoria;
import rob.proyecto.tfc.data.entity.Especialidad;


/**
 * Servicio para la logica de "Especialidades".
 * 
 * @author user
 * 
 */
@Service("MaestrosService")
public class MaestrosSrvImpl implements MaestrosService
{

	/**
	 * DAO de acceso a los datos de la entidad "Categoria".
	 */
	@Autowired
	@Qualifier("CategoriasDAO")
	private CategoriasDAO categoriasDAO;

	/**
	 * DAO de acceso a los datos de la entidad "Especialidad".
	 */
	@Autowired
	@Qualifier("EspecialidadesDAO")
	private EspecialidadesDAO especialidadesDAO;



	/**
	 * @param categoriasDAO the categoriasDAO to set
	 */
	public void setCategoriasDAO(final CategoriasDAO categoriasDAO)
	{
		this.categoriasDAO = categoriasDAO;
	}

	/**
	 * @param especialidadesDAO the especialidadesDAO to set
	 */
	public void setEspecialidadesDAO(final EspecialidadesDAO especialidadesDAO)
	{
		this.especialidadesDAO = especialidadesDAO;
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.MaestrosService#buscarCategoriasTodas()
	 */
	@Override
	public List<Categoria> buscarCategoriasTodas()
	{
		return categoriasDAO.buscarAll();
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.MaestrosService#buscarEspecialidadesTodas()
	 */
	@Override
	public List<Especialidad> buscarEspecialidadesTodas()
	{
		return especialidadesDAO.buscarAll();
	}


}
