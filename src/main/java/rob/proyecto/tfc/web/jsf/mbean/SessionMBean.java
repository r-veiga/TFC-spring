package rob.proyecto.tfc.web.jsf.mbean;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rob.proyecto.tfc.data.entity.Paciente;
import rob.proyecto.tfc.data.entity.Sanitario;
import rob.proyecto.tfc.data.entity.Usuario;
import rob.proyecto.tfc.data.vo.UsuarioVO;


/**
 * ManageBean para la gestion de elementos de la Sesion del usuario al trabajar en la aplicacion.
 * 
 * @author user
 * 
 */
@ManagedBean(name = "SessionMBean")
@SessionScoped
public class SessionMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(SessionMBean.class);


	/** Datos del Usuario que ha accedido a la aplicacion. */
	private UsuarioVO usuario = new UsuarioVO();

	/** Idioma seleccionado por el Usuario que ha accedido a la aplicacion. */
	private String idioma = "es";


	/** Entidad paciente seleccionado en la aplicacion. */
	private Paciente pacienteSelected = null;

	/** Entidad sanitario seleccionado en la aplicacion. */
	private Sanitario sanitarioSelected = null;

	/** Entidad usuario seleccionado en la aplicacion. */
	private Usuario usuarioSelected = null;


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * @return the usuario
	 */
	public final UsuarioVO getUsuario()
	{
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public final void setUsuario(final UsuarioVO usuario)
	{
		this.usuario = usuario;
	}



	/**
	 * @return the idioma
	 */
	public final String getIdioma()
	{
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public final void setIdioma(final String idioma)
	{
		this.idioma = idioma;
	}



	/**
	 * @return the paciente
	 */
	public final Paciente getPacienteSelected()
	{
		return pacienteSelected;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public final void setPacienteSelected(final Paciente paciente)
	{
		pacienteSelected = paciente;
	}


	/**
	 * @return the sanitario
	 */
	public final Sanitario getSanitarioSelected()
	{
		return sanitarioSelected;
	}

	/**
	 * @param sanitario the sanitario to set
	 */
	public final void setSanitarioSelected(final Sanitario sanitario)
	{
		sanitarioSelected = sanitario;
	}


	/**
	 * @return the usuario
	 */
	public final Usuario getUsuarioSelected()
	{
		return usuarioSelected;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public final void setUsuarioSelected(final Usuario usuario)
	{
		usuarioSelected = usuario;
	}


	//---------- Metodos de logica.


	/**
	 * Metodo para controlar la sesion a traves del menu de la aplicacion.
	 * 
	 * @param menu
	 * @return
	 */
	public String doOpcionMenu(final String menu)
	{
		LOG.info("Usuario ID: u={} / s={} - Menu: ir a '{}'", usuario.getIdUsuario(), usuario.getIdSanitario(), menu);

		pacienteSelected = null;
		sanitarioSelected = null;
		usuarioSelected = null;

		return menu;
	}


	/**
	 * Metodo para cerrar la sasion al salir de la aplicacin.
	 * 
	 * @return
	 */
	public String doLogout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
}
