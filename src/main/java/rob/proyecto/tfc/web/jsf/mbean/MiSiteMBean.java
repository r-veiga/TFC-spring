package rob.proyecto.tfc.web.jsf.mbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rob.proyecto.tfc.core.service.CitasService;
import rob.proyecto.tfc.core.service.SanitariosService;
import rob.proyecto.tfc.data.entity.Cita;
import rob.proyecto.tfc.data.entity.Nota;
import rob.proyecto.tfc.data.entity.NotaPK;


/**
 * ManageBean para la gestion de la informacion de "MiSite".
 * 
 * @author user
 * 
 */
@ManagedBean(name = "MiSiteMBean")
@ViewScoped
public class MiSiteMBean extends AbstractMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(MiSiteMBean.class);


	/** Servicio spring de gestion de citas. */
	@ManagedProperty("#{CitasService}")
	private CitasService citasSrv;

	/** Servicio spring de gestion de citas. */
	@ManagedProperty("#{SanitariosService}")
	private SanitariosService sanitarioSrv;


	private Long idSanitario = null;

	private Long idEspecialidad = null;

	private String descripcion = "";


	/** Entidad para el formulario. */
	private Nota nota = new Nota();

	/** Lista de entidaes cita. */
	private List<Cita> citas = new ArrayList<Cita>(0);

	/** Lista de entidaes nota. */
	private List<Nota> notas = new ArrayList<Nota>(0);


	//---------- Carga de los servicios Spring.


	/**
	 * @param citasSrv the citasSrv to set
	 */
	public final void setCitasSrv(final CitasService citasSrv)
	{
		this.citasSrv = citasSrv;
	}

	/**
	 * @param sanitarioSrv the sanitarioSrv to set
	 */
	public final void setSanitarioSrv(final SanitariosService sanitarioSrv)
	{
		this.sanitarioSrv = sanitarioSrv;
	}


	//---------- Inicializacion del MBean.


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.web.jsf.mbean.AbstractMBean#inicializar()
	 */
	@Override
	protected void inicializar()
	{
		idSanitario = null;
		idEspecialidad = null;

		if(null != getSessionMBean().getUsuario())
		{
			idSanitario = getSessionMBean().getUsuario().getIdSanitario();
			idEspecialidad = getSessionMBean().getUsuario().getIdEspecialidad();

			citas = citasSrv.buscarCitasEspecialidad(idEspecialidad, new Date(), null);
			notas = sanitarioSrv.buscarNotasSanitario(idSanitario);
		}
	}


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * @return the descripcion
	 */
	public final String getDescripcion()
	{
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public final void setDescripcion(final String descripcion)
	{
		this.descripcion = descripcion;
	}


	/**
	 * @return the nota
	 */
	public final Nota getNota()
	{
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public final void setNota(final Nota nota)
	{
		this.nota = nota;
	}


	/**
	 * @return the notas
	 */
	public final List<Nota> getNotas()
	{
		return notas;
	}

	/**
	 * @return the citas
	 */
	public final List<Cita> getCitas()
	{
		return citas;
	}


	//---------- Metodos de captura de eventos.


	public void onNotasRowSelect(final SelectEvent event)
	{
		nota = (Nota)event.getObject();
	}


	//---------- Metodos de logica.


	/**
	 * Metodo para crear una nota.
	 */
	public String doCrearNota()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		NotaPK pk = new NotaPK();
		pk.setIdSanitario(idSanitario);
		pk.setFecha(new Date());

		nota = new Nota();
		nota.setId(pk);
		nota.setDescripcion(descripcion);

		try
		{
			sanitarioSrv.crearNota(nota);

			notas = sanitarioSrv.buscarNotasSanitario(idSanitario);

			verMensajeInfo("crear.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error al crear Nota.", e);
			verMensajeError("crear.error");
		}

		return resultado;
	}


	/**
	 * Metodo para borrar una nota.
	 */
	public String doBorrarNota()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			sanitarioSrv.borrarNota(nota);

			notas = sanitarioSrv.buscarNotasSanitario(idSanitario);

			verMensajeInfo("borrar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error al borrar Nota.", e);
			verMensajeError("borrar.error");
		}

		return resultado;
	}


}
