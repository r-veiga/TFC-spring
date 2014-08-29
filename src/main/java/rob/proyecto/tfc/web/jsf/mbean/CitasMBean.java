package rob.proyecto.tfc.web.jsf.mbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rob.proyecto.tfc.core.service.CitasService;
import rob.proyecto.tfc.data.entity.Cita;
import rob.proyecto.tfc.data.entity.CitaPK;
import rob.proyecto.tfc.data.vo.ConsultaHoraVO;


/**
 * ManageBean para la gestion de Citas.
 * 
 * @author user
 * 
 */
@ManagedBean(name = "CitasMBean")
@ViewScoped
public class CitasMBean extends AbstractMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(CitasMBean.class);


	/** Servicio spring de gestion de citas. */
	@ManagedProperty("#{CitasService}")
	private CitasService citasSrv;


	/** Entidad para el manejo de la lista. */
	private Cita cita = new Cita();

	/** Lista de entidades. */
	private List<Cita> citas = new ArrayList<Cita>(0);


	/** Cita de un dia. */
	private ConsultaHoraVO consulta = new ConsultaHoraVO();


	/** Lista de citas de un dia para seleccionar una cita nueva. */
	private List<ConsultaHoraVO> consultas = new ArrayList<ConsultaHoraVO>(0);


	//---------- Carga del servicio Spring.


	/**
	 * @param citasSrv the citasSrv to set
	 */
	public final void setCitasSrv(final CitasService citasSrv)
	{
		this.citasSrv = citasSrv;
	}


	//---------- Inicializacion del MBean.


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.web.jsf.mbean.AbstractMBean#inicializar()
	 */
	@Override
	protected void inicializar()
	{
		CitaPK pk = new CitaPK();
		pk.setFecha(new Date());

		cita = new Cita();
		cita.setId(pk);
	}


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * @return the cita
	 */
	public final Cita getCita()
	{
		return cita;
	}

	/**
	 * @param cita the cita to set
	 */
	public final void setCita(final Cita cita)
	{
		this.cita = cita;
	}


	/**
	 * @return the citas
	 */
	public final List<Cita> getCitas()
	{
		return citas;
	}

	/**
	 * @param citas the citas to set
	 */
	public final void setCitas(final List<Cita> citas)
	{
		this.citas = citas;
	}


	/**
	 * @return the consulta
	 */
	public final ConsultaHoraVO getConsulta()
	{
		return consulta;
	}

	/**
	 * @param consulta the consulta to set
	 */
	public final void setConsulta(final ConsultaHoraVO consulta)
	{
		this.consulta = consulta;
	}


	/**
	 * @return the consultas
	 */
	public final List<ConsultaHoraVO> getConsultas()
	{
		return consultas;
	}

	/**
	 * Metodo para obtener la 1a mitad de la lista de consultas.
	 * 
	 * @return the consultas
	 */
	public final List<ConsultaHoraVO> getConsultasMitad1()
	{
		int mitad = consultas.size() / 2;

		return consultas.subList(0, mitad);
	}

	/**
	 * Metodo para obtener la 2a mitad de la lista de consultas.
	 * 
	 * @return the consultas
	 */
	public final List<ConsultaHoraVO> getConsultasMitad2()
	{
		int mitad = consultas.size() / 2;

		return consultas.subList(mitad, consultas.size());

	}

	/**
	 * @param consultas the consultas to set
	 */
	public final void setConsultas(final List<ConsultaHoraVO> consultas)
	{
		this.consultas = consultas;
	}


	//---------- Metodos de logica.


	/**
	 * Metodo para buscar las proximas citas de una especialidad.
	 */
	public void doBuscarCitasEspecialidadProximas()
	{
		citas = citasSrv.buscarCitasEspecialidad(cita.getId().getIdEspecialidad(), cita.getId().getFecha(), null);
	}


	/**
	 * Metodo para buscar las citas de una especialidad para un dia.
	 */
	public void doBuscarCitasEspecialidadDia()
	{
		Long idEspecialidad = cita.getId().getIdEspecialidad();
		Date fecha = cita.getId().getFecha();

		consultas = citasSrv.buscarHorasConsultaEspecialidad(idEspecialidad, fecha);
	}


	/**
	 * Metodo para reservar una cita.
	 */
	public String doReservarCita()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			// Cargamos la consultaVO seleccionada desde la pagina.
			cita = consulta.getCita();

			// Establecemos el Id de paciente
			cita.getId().setIdPaciente(getSessionMBean().getPacienteSelected().getId());

			citasSrv.crearCita(cita);

			verMensajeInfo("crear.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error creando cita.", e);
			verMensajeError("crear.error");
		}

		return resultado;
	}


	/**
	 * Metodo para anular una cita.
	 */
	public String doAnularCita()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			citasSrv.borrarCita(cita);

			verMensajeInfo("borrar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error al borrar Cita.", e);
			verMensajeError("borrar.error");
		}

		return resultado;
	}


}
