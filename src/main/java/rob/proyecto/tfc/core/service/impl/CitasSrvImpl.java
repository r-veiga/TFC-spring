package rob.proyecto.tfc.core.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.TFCConstantes;
import rob.proyecto.tfc.core.dao.CitasDAO;
import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.CitasService;
import rob.proyecto.tfc.data.entity.Cita;
import rob.proyecto.tfc.data.entity.CitaPK;
import rob.proyecto.tfc.data.vo.ConsultaHoraVO;


/**
 * Servicio para la logica de "Citas".
 * 
 * @author user
 * 
 */
@Service("CitasService")
public class CitasSrvImpl implements CitasService
{

	/**
	 * DAO de acceso a los datos de la entidad "Cita".
	 */
	@Autowired
	@Qualifier("CitasDAO")
	private CitasDAO citasDAO;


	/**
	 * @param citasDAO the citasDAO to set
	 */
	public void setCitasDAO(final CitasDAO citasDAO)
	{
		this.citasDAO = citasDAO;
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.CitasService#buscarCitasEspecialidad(java.lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Cita> buscarCitasEspecialidad(final Long idEspecialidad, final Date fechaDesde, final Date fechaHasta)
	{
		return citasDAO.buscarCitasEspecialidad(idEspecialidad, fechaDesde, fechaHasta);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.CitasService#buscarCitasPaciente(java.lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Cita> buscarCitasPaciente(final Long idPaciente, final Date fechaDesde, final Date fechaHasta)
	{
		return citasDAO.buscarCitasPaciente(idPaciente, fechaDesde, fechaHasta);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.CitasService#buscarHorasConsultaEspecialidad(java.lang.Long, java.util.Date)
	 */
	@Override
	public List<ConsultaHoraVO> buscarHorasConsultaEspecialidad(final Long idEspecialidad, final Date fecha)
	{
		//- Inicilizamos la lista de consultas del dia.
		List<ConsultaHoraVO> consultas = new ArrayList<ConsultaHoraVO>(0);


		// Solo se buscan las citas para un dia, desde las 0h desde hasta 24 (1 dia) despues.

		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.add(Calendar.DATE, 1);

		Date fHasta = c.getTime();

		List<Cita> citas = citasDAO.buscarCitasEspecialidad(idEspecialidad, fecha, fHasta);


		// Comprobamos las citas existentes con las horas de consulta, para montar una lista
		// de horas libres y reservadas.
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


		//- Inicializamos el calendario con la primera hora de cita
		long h = TFCConstantes.CONSULTAS_HORA_INICIO;

		c.setTime(fecha);
		c.set(Calendar.HOUR_OF_DAY, (int)(h / TFCConstantes.MILISEC_HORA));
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		c.add(Calendar.MINUTE, -30);

		//- Recorremos todas las horas de visita y comprobamos si hay citas para esa hora.
		// y montamos la lista de citas del dia.
		for(; h <= TFCConstantes.CONSULTAS_HORA_FIN; h += TFCConstantes.CUNSULTA_DURACION)
		{
			c.add(Calendar.MINUTE, 30);

			String horaDia = "" + sdf.format(c.getTime());

			CitaPK pk = new CitaPK();
			pk.setIdEspecialidad(idEspecialidad);
			pk.setFecha(c.getTime());

			Cita cita = new Cita();
			cita.setId(pk);

			int libre = 1;

			for(int j = 0; j < citas.size(); j++)
			{
				if(horaDia.equals(sdf.format(citas.get(j).getId().getFecha())))
				{
					cita = citas.get(j);
					libre = 0;
					j = citas.size();
				}
			}

			ConsultaHoraVO consulta = new ConsultaHoraVO();
			consulta.setHora(horaDia);
			consulta.setCita(cita);
			consulta.setLibre(libre);

			consultas.add(consulta);
		}


		return consultas;
	}



	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.CitasService#crearCita(rob.proyecto.tfc.data.entity.Cita)
	 */
	@Override
	@Transactional
	public Cita crearCita(final Cita cita) throws TFCException
	{
		return citasDAO.crear(cita);
	}


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.core.service.CitasService#borrarCita(rob.proyecto.tfc.data.entity.Cita)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Cita borrarCita(final Cita cita) throws TFCException
	{
		Cita citaBorrar = citasDAO.buscarPK(cita, cita.getId());

		return citasDAO.borrar(citaBorrar);
	}
}
