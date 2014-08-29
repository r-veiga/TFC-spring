package rob.proyecto.tfc.core.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.core.dao.PacientesDAO;
import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.PacientesService;
import rob.proyecto.tfc.data.entity.Paciente;


/**
 * Servicio para la logica de "Pacientes".
 * 
 * @author user
 * 
 */
@Service("PacientesService")
public class PacientesSrvImpl implements PacientesService
{

	/**
	 * DAO de acceso a los datos de la entidad "Paciente".
	 */
	@Autowired
	@Qualifier("PacientesDAO")
	private PacientesDAO pacientesDAO;


	/**
	 * @param pacientesDAO the pacientesDAO to set
	 */
	public void setPacientesDAO(final PacientesDAO pacientesDAO)
	{
		this.pacientesDAO = pacientesDAO;
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.PacientesService#buscarPaciente(java.lang.Long)
	 */
	@Override
	public Paciente buscarPaciente(final Long idPaciente)
	{
		return pacientesDAO.buscarPK(new Paciente(), idPaciente);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.PacientesService#buscarPacientes(rob.proyecto.tfc.data.entity.Paciente)
	 */
	@Override
	public List<Paciente> buscarPacientes(final Paciente paciente)
	{
		return pacientesDAO.buscarByExample(paciente);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.PacientesService#crearPaciente(rob.proyecto.tfc.data.entity.Paciente)
	 */
	@Override
	@Transactional
	public Paciente crearPaciente(final Paciente paciente) throws TFCException
	{
		paciente.setFecAlta(new Date());

		return pacientesDAO.crear(paciente);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.PacientesService#modificarPaciente(rob.proyecto.tfc.data.entity.Paciente)
	 */
	@Override
	@Transactional
	public Paciente modificarPaciente(final Paciente paciente) throws TFCException
	{
		return pacientesDAO.modificar(paciente);
	}


}
