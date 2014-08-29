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

import rob.proyecto.tfc.core.service.UsuariosService;
import rob.proyecto.tfc.data.entity.Sanitario;
import rob.proyecto.tfc.data.entity.Usuario;
import rob.proyecto.tfc.data.vo.UsuarioVO;


/**
 * ManageBean para la gestion de Usuarios.
 * 
 * @author user
 * 
 */
@ManagedBean(name = "UsuariosMBean")
@ViewScoped
public class UsuariosMBean extends AbstractMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(UsuariosMBean.class);


	/** Servicio spring de gestion de Usuarios. */
	@ManagedProperty("#{UsuariosService}")
	private UsuariosService usuariosSrv;


	/** Lista de entidades usuario obtenoda de la capa CORE. */
	private List<Usuario> usuarios = new ArrayList<Usuario>(0);

	/** Lista de entidad usuario filtrada desde JSF. */
	private List<Usuario> listaDatosTablaFiltrados = null;


	/** Entidad para el formulario. */
	private Usuario usuario = new Usuario();


	//---------- Carga del servicio Spring.


	/**
	 * @param usuariosSrv the usuariosSrv to set
	 */
	public final void setUsuariosSrv(final UsuariosService usuariosSrv)
	{
		this.usuariosSrv = usuariosSrv;
	}


	//---------- Inicializacion del MBean.


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.web.jsf.mbean.AbstractMBean#inicializar()
	 */
	@Override
	protected void inicializar()
	{
		LOG.info("Inicializando MBean");


		if(getSessionMBean().getUsuarioSelected() != null)
		{
			usuario = getSessionMBean().getUsuarioSelected();
		}
		else
		{
			usuario = new Usuario();
		}
	}


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * @return the usuario
	 */
	public final Usuario getUsuario()
	{
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public final void setUsuario(final Usuario usuario)
	{
		this.usuario = usuario;
	}


	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios()
	{
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public final void setUsuarios(final List<Usuario> usuarios)
	{
		this.usuarios = usuarios;
	}


	/**
	 * @return the listaDatosTablaFiltrados
	 */
	public List<Usuario> getListaDatosTablaFiltrados()
	{
		return listaDatosTablaFiltrados;
	}

	/**
	 * @param listaDatosTablaFiltrados the listaDatosTablaFiltrados to set
	 */
	public final void setListaDatosTablaFiltrados(final List<Usuario> listaDatosTablaFiltrados)
	{
		this.listaDatosTablaFiltrados = listaDatosTablaFiltrados;
	}


	/**
	 * Metodo para buscar personal sanitario sin usuario asignado.
	 * 
	 * @return
	 */
	public List<Sanitario> getSanitariosSinUsuario()
	{
		return usuariosSrv.buscarSanitariosSinUsuario();
	}


	//---------- Metodos de captura de eventos.


	public void onRowSelect(final SelectEvent event)
	{
		usuario = (Usuario)event.getObject();
	}

	public void onRowUnselect(final UnselectEvent event)
	{
		usuario = null;
	}

	public void onSanitarioRowSelect(final SelectEvent event)
	{
		Sanitario san = (Sanitario)event.getObject();

		usuario.setNombre(san.getNombre());
		usuario.setApellidos(san.getApellidos());
		usuario.setEmail(san.getEmail());
	}

	public void onSanitarioUnselect(final UnselectEvent event)
	{
		usuario.setNombre(null);
		usuario.setApellidos(null);
		usuario.setEmail(null);
	}


	//---------- Metodos de logica.


	/**
	 * Metodo para validar el usuario y permitir el acceso a la aplicacion.
	 * 
	 * @return
	 */
	public String doLogin()
	{
		LOG.info("Inicio de la validacion");

		String accion = JSF_ACT_RESPONSE_FAILURE;

		if(usuario != null && usuario.getUser() != null && usuario.getPass() != null)
		{
			LOG.info("Invocacion al Service");

			UsuarioVO resultado = null;

			try
			{
				resultado = usuariosSrv.validarUsuario(usuario);
			}
			catch(Exception e)
			{
				LOG.error("Error en login", e);
			}

			LOG.info("Invocacion al Service. Resultado = {}", resultado);


			if(resultado != null)
			{
				getSessionMBean().setUsuario(resultado);
				accion = JSF_ACT_RESPONSE_SUCCESS;
			}
			else
			{
				verMensajeError("login.error");
			}
		}

		LOG.info("Fin de la validacion : Accion {}", accion);

		return accion;
	}



	/**
	 * Metodo para buscar usuarios a partir de los datos del objeto "usuario".
	 */
	public void doBuscarTodos()
	{
		validarDatosParaBusqueda();

		LOG.info("Criterios de busqueda {} " + usuario);

		usuarios = usuariosSrv.buscarUsuarios(usuario);

		// - Reseteamos la entidad para el formulaio de busqueda
		// y para asignarle el registro de la tabla seleccionado
		usuario = new Usuario();
	}


	/**
	 * Metodo para buscar usuarios a partir de los datos del objeto "usuario".
	 * 
	 * @return
	 */
	public String doVerDatosUsuario()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		if(null != usuario)
		{
			getSessionMBean().setUsuarioSelected(usuario);

			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}

		return resultado;
	}



	/**
	 * Metodo para crear un usuario.
	 * 
	 * @return
	 */
	public String doCrearUsuario()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			usuariosSrv.crearUsuario(usuario);

			verMensajeInfo("crear.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error creando Usuario", e);
			verMensajeInfo("crear.err");
		}

		return resultado;
	}


	/**
	 * Metodo para actualzair los datos de un usuario.
	 * 
	 * @return
	 */
	public String doActualizarUsuario()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			usuariosSrv.modificarUsuario(usuario);

			verMensajeInfo("modificar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error modificando Usuario", e);
			verMensajeInfo("modificar.err");
		}

		return resultado;
	}


	/**
	 * Metodo para dar de baja logica un usuario.
	 * 
	 * @return
	 */
	public String doBorrarUsuario()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		try
		{
			usuario.setFecBaja(new Date());

			usuariosSrv.modificarUsuario(usuario);

			verMensajeInfo("borrar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error modificando Usuario", e);
			verMensajeInfo("borrar.err");
		}

		return resultado;
	}



	/**
	 * Metodo para la preparar los valores de los formularios para la ejecucion de busquedas.
	 */
	private void validarDatosParaBusqueda()
	{
		if(null == usuario)
		{
			usuario = new Usuario();
		}

		if(usuario.getNombre() == null || "".equalsIgnoreCase(usuario.getNombre().trim()))
		{
			usuario.setNombre(null);
		}

		if(usuario.getApellidos() == null || "".equalsIgnoreCase(usuario.getApellidos().trim()))
		{
			usuario.setApellidos(null);
		}
	}


}
