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

import rob.proyecto.tfc.core.service.SanitariosService;
import rob.proyecto.tfc.data.entity.Sanitario;


/**
 * ManageBean para la gestion de Sanitarios.
 * 
 * @author user
 * 
 */
@ManagedBean(name = "SanitariosMBean")
@ViewScoped
public class SanitariosMBean extends AbstractMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(SanitariosMBean.class);


	/** Servicio spring de gestion de Sanitarios. */
	@ManagedProperty("#{SanitariosService}")
	private SanitariosService sanitariosSrv;


	/** Lista de entidades sanitario obtenoda de la capa CORE. */
	private List<Sanitario> sanitarios = new ArrayList<Sanitario>(0);

	/** Lista de entidad sanitario filtrada desde JSF. */
	private List<Sanitario> listaDatosTablaFiltrados = null;


	/** Entidad para el formulario. */
	private Sanitario sanitario = new Sanitario();


	//---------- Carga del servicio Spring.


	/**
	 * @param sanitariosSrv the sanitariosSrv to set
	 */
	public final void setSanitariosSrv(final SanitariosService sanitariosSrv)
	{
		this.sanitariosSrv = sanitariosSrv;
	}


	//---------- Inicializacion del MBean.


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.web.jsf.mbean.AbstractMBean#inicializar()
	 */
	@Override
	protected void inicializar()
	{
		LOG.info("Inicializando MBean");


		if(getSessionMBean().getSanitarioSelected() != null)
		{
			sanitario = getSessionMBean().getSanitarioSelected();
		}
		else
		{
			sanitario = new Sanitario();
		}
	}


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * @return the usuario
	 */
	public final Sanitario getSanitario()
	{
		return sanitario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public final void setSanitario(final Sanitario sanitario)
	{
		this.sanitario = sanitario;
	}


	/**
	 * @return the sanitarios
	 */
	public List<Sanitario> getSanitarios()
	{
		return sanitarios;
	}

	/**
	 * @param sanitarios the sanitarios to set
	 */
	public final void setSanitarios(final List<Sanitario> sanitarios)
	{
		this.sanitarios = sanitarios;
	}


	/**
	 * @return the listaDatosTablaFiltrados
	 */
	public List<Sanitario> getListaDatosTablaFiltrados()
	{
		return listaDatosTablaFiltrados;
	}

	/**
	 * @param listaDatosTablaFiltrados the listaDatosTablaFiltrados to set
	 */
	public final void setListaDatosTablaFiltrados(final List<Sanitario> listaDatosTablaFiltrados)
	{
		this.listaDatosTablaFiltrados = listaDatosTablaFiltrados;
	}


	//---------- Metodos de captura de eventos.


	public void onRowSelect(final SelectEvent event)
	{
		sanitario = (Sanitario)event.getObject();
	}

	public void onRowUnselect(final UnselectEvent event)
	{
		sanitario = null;
	}


	//---------- Metodos de logica.


	/**
	 * Metodo para buscar sanitarios a partir de los datos del objeto "sanitario".
	 */
	public void doBuscarTodos()
	{
		validarDatosParaBusqueda();

		LOG.info("Criterios de busqueda {} " + sanitario);

		sanitarios = sanitariosSrv.buscarSanitarios(sanitario);

		// - Reseteamos la entidad para el formulaio de busqueda
		// y para asignarle el registro de la tabla seleccionado
		sanitario = new Sanitario();
	}


	/**
	 * Metodo para buscar sanitarios a partir de los datos del objeto "sanitario".
	 */
	public String doVerDatosSanitario()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		if(null != sanitario)
		{
			getSessionMBean().setSanitarioSelected(sanitario);

			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}

		return resultado;
	}


	/**
	 * Metodo para crear un sanitario.
	 * 
	 * @return
	 */
	public String doCrearSanitario()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			sanitariosSrv.crearSanitario(sanitario);

			verMensajeInfo("crear.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error creando Sanitario", e);
			verMensajeInfo("crear.err");
		}

		return resultado;
	}


	/**
	 * Metodo para actualzair los datos de un sanitario.
	 * 
	 * @return
	 */
	public String doActualizarSanitario()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			sanitariosSrv.modificarSanitario(sanitario);

			verMensajeInfo("modificar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error modificando Sanitario", e);
			verMensajeInfo("modificar.err");
		}

		return resultado;
	}


	/**
	 * Metodo para dar de baja logica un sanitario.
	 * 
	 * @return
	 */
	public String doBorrarSanitario()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			sanitario.setFecBaja(new Date());

			sanitariosSrv.modificarSanitario(sanitario);

			verMensajeInfo("borrar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error modificando Sanitario", e);
			verMensajeInfo("borrar.err");
		}

		return resultado;
	}



	/**
	 * Metodo para la preparar los valores de los formularios para la ejecucion de busquedas.
	 */
	private void validarDatosParaBusqueda()
	{
		if(null == sanitario)
		{
			sanitario = new Sanitario();
		}

		if(sanitario.getColegiado() == null || "".equalsIgnoreCase(sanitario.getColegiado().trim()))
		{
			sanitario.setColegiado(null);
		}

		if(sanitario.getIdEspecialidad() == null || sanitario.getIdEspecialidad() == 0L)
		{
			sanitario.setIdEspecialidad(null);
		}
	}
}
