package rob.proyecto.tfc.core.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.core.dao.NotasDAO;
import rob.proyecto.tfc.core.dao.SanitariosDAO;
import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.SanitariosService;
import rob.proyecto.tfc.data.entity.Nota;
import rob.proyecto.tfc.data.entity.NotaPK;
import rob.proyecto.tfc.data.entity.Sanitario;


/**
 * Servicio para la logica de "Sanitarios".
 * 
 * @author user
 * 
 */
@Service("SanitariosService")
public class SanitariosSrvImpl implements SanitariosService
{

	/**
	 * DAO de acceso a los datos de la entidad "Sanitario".
	 */
	@Autowired
	@Qualifier("SanitariosDAO")
	private SanitariosDAO sanitariosDAO;

	/**
	 * DAO de acceso a los datos de la entidad "Nota".
	 */
	@Autowired
	@Qualifier("NotasDAO")
	private NotasDAO notasDAO;


	/**
	 * @param dao the dao to set
	 */
	public void setSanitariosDAO(final SanitariosDAO sanitariosDAO)
	{
		this.sanitariosDAO = sanitariosDAO;
	}

	/**
	 * @param notasDAO the notasDAO to set
	 */
	public void setNotasDAO(final NotasDAO notasDao)
	{
		notasDAO = notasDao;
	}



	//-- Metodos de gestion de Sanitario


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.SanitariosService#buscarSanitario(java.lang.Long)
	 */
	@Override
	public Sanitario buscarSanitario(final Long idSanitario)
	{
		return sanitariosDAO.buscarPK(new Sanitario(), idSanitario);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.SanitariosService#buscarSanitarios(rob.proyecto.tfc.data.entity.Sanitario)
	 */
	@Override
	public List<Sanitario> buscarSanitarios(final Sanitario sanitario)
	{
		if(null == sanitario)
		{
			return sanitariosDAO.buscarAll();
		}
		else
		{
			return sanitariosDAO.buscarByExample(sanitario);
		}
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.SanitariosService#crearSanitario(rob.proyecto.tfc.data.entity.Sanitario)
	 */
	@Override
	@Transactional
	public Sanitario crearSanitario(final Sanitario sanitario) throws TFCException
	{
		sanitario.setFecAlta(new Date());

		return sanitariosDAO.crear(sanitario);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.SanitariosService#modificarSanitario(rob.proyecto.tfc.data.entity.Sanitario)
	 */
	@Override
	@Transactional
	public Sanitario modificarSanitario(final Sanitario sanitario) throws TFCException
	{
		return sanitariosDAO.modificar(sanitario);
	}



	//-- Metodos de gestion de Notas de sanitario.

	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.SanitariosService#buscarNotasSanitario(java.lang.Long)
	 */
	@Override
	public List<Nota> buscarNotasSanitario(final Long idSanitario)
	{
		NotaPK pk = new NotaPK();
		pk.setIdSanitario(idSanitario);

		Nota nota = new Nota();
		nota.setId(pk);

		return notasDAO.buscarByExample(nota);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.SanitariosService#crearNota(rob.proyecto.tfc.data.entity.Nota)
	 */
	@Override
	@Transactional
	public Nota crearNota(final Nota nota) throws TFCException
	{
		return notasDAO.crear(nota);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.SanitariosService#borrarNota(rob.proyecto.tfc.data.entity.Nota)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Nota borrarNota(final Nota nota) throws TFCException
	{
		Nota notaBorrar = notasDAO.buscarPK(nota, nota.getId());

		return notasDAO.borrar(notaBorrar);
	}


}
