package rob.proyecto.tfc.web.jsf.mbean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rob.proyecto.tfc.TFCConstantes;
import rob.proyecto.tfc.core.service.AnotacionesService;
import rob.proyecto.tfc.data.entity.Anotacion;
import rob.proyecto.tfc.data.entity.AnotacionPK;


/**
 * ManageBean para la gestion de Anotaciones.
 * 
 * @author user
 * 
 */
@ManagedBean(name = "AnotacionesMBean")
@ViewScoped
public class AnotacionesMBean extends AbstractMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(AnotacionesMBean.class);


	/** Servicio spring de gestion de anotaciones. */
	@ManagedProperty(value = "#{AnotacionesService}")
	private AnotacionesService anotacionesSrv;


	/** Lista de entidades. */
	private List<Anotacion> anotaciones;

	/** Pestania actual. Por defecto 'tabMED'. */
	private String sTab = "tabMED";


	/** Texto del formulario de alta de una nueva anotacion. */
	private String texto = null;


	//---------- Carga del servicio Spring.


	/**
	 * @param anotacionesSrv the anotacionesSrv to set
	 */
	public final void setAnotacionesSrv(final AnotacionesService anotacionesSrv)
	{
		this.anotacionesSrv = anotacionesSrv;
	}


	//---------- Inicializacion del MBean.


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.web.jsf.mbean.AbstractMBean#inicializar()
	 */
	@Override
	protected void inicializar()
	{
		cargarDatos();
	}


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * @return the anotaciones
	 */
	public final List<Anotacion> getAnotaciones()
	{
		return anotaciones;
	}

	/**
	 * @param anotaciones the anotaciones to set
	 */
	public final void setAnotaciones(final List<Anotacion> anotaciones)
	{
		this.anotaciones = anotaciones;
	}


	/**
	 * @return the texto
	 */
	public final String getTexto()
	{
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public final void setTexto(final String texto)
	{
		this.texto = texto;
	}


	//---------- Metodos de logica.


	/**
	 * Metodo para capturar el evento de cambio de TAB.
	 * 
	 * @param event
	 */
	public void cambioTab(final TabChangeEvent event)
	{
		sTab = event.getTab().getId();

		LOG.info("Cambio de pestania id='{}' :: '{}'", sTab, event.getTab());

		cargarDatos();
	}


	/**
	 * Metodo para crear una nueva anotacion.
	 */
	public String doCrearAnotacion()
	{
		String retorno = JSF_ACT_RESPONSE_FAILURE;

		AnotacionPK id = new AnotacionPK();
		id.setFecha(new Date());
		id.setIdPaciente(getSessionMBean().getPacienteSelected().getId());
		id.setIdSanitario(getSessionMBean().getUsuario().getIdSanitario());

		Anotacion anotacion = new Anotacion();
		anotacion.setId(id);
		anotacion.setTexto(texto);

		try
		{
			anotacionesSrv.crearAnotacion(anotacion);

			cargarDatos();

			verMensajeInfo("crear.ok");
			retorno = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error creando anotacion.", e);
			verMensajeError("crear.error");
		}

		return retorno;
	}


	/**
	 * Metodo para la carga e inicializacion de los datos de la pantalla.
	 */
	private void cargarDatos()
	{
		Long idPaciente = getSessionMBean().getPacienteSelected().getId();

		TFCConstantes.TIPO_SANITARIO tipoSanitario = TFCConstantes.TIPO_SANITARIO.MEDICO;

		if("tabMED".equals(sTab))
		{
			tipoSanitario = TFCConstantes.TIPO_SANITARIO.MEDICO;
		}

		if("tabENF".equals(sTab))
		{
			tipoSanitario = TFCConstantes.TIPO_SANITARIO.ENFERMERIA;
		}

		if("tabAUX".equals(sTab))
		{
			tipoSanitario = TFCConstantes.TIPO_SANITARIO.AUXILIAR;
		}

		if("tabREA".equals(sTab))
		{
			tipoSanitario = TFCConstantes.TIPO_SANITARIO.REHABILITACION;
		}

		if("tabTSOC".equals(sTab))
		{
			tipoSanitario = TFCConstantes.TIPO_SANITARIO.TRABAJO_SOCIAL;
		}

		LOG.info("Cargando datos del paciente: '{}' para tipo sanitario: '{}'", idPaciente, tipoSanitario.toString());

		anotaciones = anotacionesSrv.buscarNotasPacientePorEditor(idPaciente, tipoSanitario);

		texto = "";
	}

}
