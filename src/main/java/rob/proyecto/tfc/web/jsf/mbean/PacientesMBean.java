package rob.proyecto.tfc.web.jsf.mbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rob.proyecto.tfc.core.service.CitasService;
import rob.proyecto.tfc.core.service.PacientesService;
import rob.proyecto.tfc.core.service.PruebasService;
import rob.proyecto.tfc.data.entity.Cita;
import rob.proyecto.tfc.data.entity.Paciente;
import rob.proyecto.tfc.data.entity.Prueba;


/**
 * ManageBean para la gestion de Pacientes.
 * 
 * @author user
 * 
 */
@ManagedBean(name = "PacientesMBean")
@ViewScoped
public class PacientesMBean extends AbstractMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(PacientesMBean.class);


	/** Servicio spring de gestion de Pacientes. */
	@ManagedProperty("#{PacientesService}")
	private PacientesService pacientesSrv;

	/** Servicio spring de gestion de Citas. */
	@ManagedProperty("#{CitasService}")
	private CitasService citasSrv;

	/** Servicio spring de gestion de Pruebas. */
	@ManagedProperty("#{PruebasService}")
	private PruebasService pruebasSrv;


	/** Lista de entidades paciente obtenoda de la capa CORE. */
	private List<Paciente> pacientes = new ArrayList<Paciente>(0);

	/** Lista de entidad paciente filtrada desde JSF. */
	private List<Paciente> listaDatosTablaFiltrados = null;


	/** Entidad para el formulario. */
	private Paciente paciente = new Paciente();

	/** Lista de entidad. */
	private List<Cita> citas = null;

	/** Lista de entidad. */
	private List<Prueba> pruebas = null;


	//---------- Carga de los servicios Spring.


	/**
	 * @param pacientesSrv the pacientesSrv to set
	 */
	public final void setPacientesSrv(final PacientesService pacientesSrv)
	{
		this.pacientesSrv = pacientesSrv;
	}

	/**
	 * @param citasSrv the citasSrv to set
	 */
	public final void setCitasSrv(final CitasService citasSrv)
	{
		this.citasSrv = citasSrv;
	}

	/**
	 * @param pruebasSrv the pruebasSrv to set
	 */
	public final void setPruebasSrv(final PruebasService pruebasSrv)
	{
		this.pruebasSrv = pruebasSrv;
	}


	//---------- Inicializacion del MBean.


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.web.jsf.mbean.AbstractMBean#inicializar()
	 */
	@Override
	protected void inicializar()
	{
		LOG.info("Inicializando MBean");

		if(getSessionMBean().getPacienteSelected() != null)
		{
			paciente = getSessionMBean().getPacienteSelected();

			citas = citasSrv.buscarCitasPaciente(paciente.getId(), new Date(), null);
			pruebas = pruebasSrv.buscarPruebasdePaciente(paciente.getId());

		}
		else
		{
			paciente = new Paciente();
			citas = null;
			pruebas = null;
		}
	}


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * @return the usuario
	 */
	public final Paciente getPaciente()
	{
		return paciente;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public final void setPaciente(final Paciente paciente)
	{
		this.paciente = paciente;
	}


	/**
	 * @return the pacientes
	 */
	public List<Paciente> getPacientes()
	{
		return pacientes;
	}

	/**
	 * @param pacientes the pacientes to set
	 */
	public final void setPacientes(final List<Paciente> pacientes)
	{
		this.pacientes = pacientes;
	}


	/**
	 * @return the listaDatosTablaFiltrados
	 */
	public List<Paciente> getListaDatosTablaFiltrados()
	{
		return listaDatosTablaFiltrados;
	}

	/**
	 * @param listaDatosTablaFiltrados the listaDatosTablaFiltrados to set
	 */
	public final void setListaDatosTablaFiltrados(final List<Paciente> listaDatosTablaFiltrados)
	{
		this.listaDatosTablaFiltrados = listaDatosTablaFiltrados;
	}



	/**
	 * @return
	 */
	public List<Cita> getCitas()
	{
		return citas;
	}

	/**
	 * @return
	 */
	public List<Prueba> getPruebas()
	{
		return pruebas;
	}


	//---------- Metodos de captura de eventos.


	public void onRowSelect(final SelectEvent event)
	{
		// FacesMessage msg = new FacesMessage("Car Selected", ((Paciente) event.getObject()).getId().toString());
		// FacesContext.getCurrentInstance().addMessage(null, msg);

		paciente = (Paciente)event.getObject();
	}

	public void onRowUnselect(final UnselectEvent event)
	{
		// FacesMessage msg = new FacesMessage("Car Unselected", ((Paciente) event.getObject()).getId().toString());
		// FacesContext.getCurrentInstance().addMessage(null, msg);

		paciente = null;
	}


	//---------- Metodos de logica.


	/**
	 * Metodo para buscar pacientes a partir de los datos del objeto "paciente".
	 */
	public void doBuscarTodos()
	{
		validarDatosParaBusqueda();

		paciente.setHospitalizado(null);

		LOG.info("Criterios de busqueda {} " + paciente);

		pacientes = pacientesSrv.buscarPacientes(paciente);

		// - Reseteamos la entidad para el formulaio de busqueda
		// y para asignarle el registro de la tabla seleccionado
		paciente = new Paciente();
	}


	public void doBuscarHospitalizados()
	{
		validarDatosParaBusqueda();

		paciente.setHospitalizado(Boolean.TRUE);

		LOG.info("Criterios de busqueda {} " + paciente);

		pacientes = pacientesSrv.buscarPacientes(paciente);

		// - Reseteamos la entidad para el formulaio de busqueda
		// y para asignarle el registro de la tabla seleccionado
		paciente = new Paciente();
	}


	/**
	 * Metodo para buscar pacientes a partir de los datos del objeto "paciente".
	 */
	public String doVerDatosPaciente()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		if(null != paciente)
		{
			getSessionMBean().setPacienteSelected(paciente);

			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}

		return resultado;
	}


	/**
	 * Metodo para crear un paciente.
	 * 
	 * @return
	 */
	public String doCrearPaciente()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			pacientesSrv.crearPaciente(paciente);

			verMensajeInfo("crear.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error creando Paciente", e);
			verMensajeInfo("crear.err");
		}

		return resultado;
	}


	/**
	 * Metodo para actualzair los datos de un paciente.
	 * 
	 * @return
	 */
	public String doActualizarPaciente()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			pacientesSrv.modificarPaciente(paciente);

			verMensajeInfo("modificar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error modificando Paciente", e);
			verMensajeInfo("modificar.err");
		}

		return resultado;
	}


	/**
	 * Metodo para dar de baja logica un paciente.
	 * 
	 * @return
	 */
	public String doBorrarPaciente()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			paciente.setFecBaja(new Date());

			pacientesSrv.modificarPaciente(paciente);

			verMensajeInfo("borrar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error modificando Paciente", e);
			verMensajeInfo("borrar.err");
		}

		return resultado;
	}



	/**
	 * Metodo para la preparar los valores de los formularios para la ejecucion de busquedas.
	 */
	private void validarDatosParaBusqueda()
	{
		if(null == paciente)
		{
			paciente = new Paciente();
		}

		if(paciente.getExpediente() == null || "".equalsIgnoreCase(paciente.getExpediente().trim()))
		{
			paciente.setExpediente(null);
		}

		if(paciente.getDni() == null || "".equalsIgnoreCase(paciente.getDni().trim()))
		{
			paciente.setDni(null);
		}
	}


}
