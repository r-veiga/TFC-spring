package rob.proyecto.tfc.web.jsf.mbean;


import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


/**
 * ManageBean abstracto para la definicion de funcionalidades comunes.
 * 
 * @author user
 * 
 */
public abstract class AbstractMBean
{

	/** CTE de navegacion JSF: success, */
	public static final String JSF_ACT_RESPONSE_SUCCESS = "success";

	/** CTE de navegacion JSF: failure. */
	public static final String JSF_ACT_RESPONSE_FAILURE = "failure";


	/** Bundle de mensajes de la aplicacion. */
	@ManagedProperty("#{msgs}")
	private static ResourceBundle msgBundle;


	/** MBean de Sesion. */
	@ManagedProperty(value = "#{SessionMBean}")
	private SessionMBean sessionMBean;


	public AbstractMBean() {

	}



	/**
	 * Metodo para la inicializacion del MBean de forma automatica tras su crreacion.
	 */
	@PostConstruct
	protected abstract void inicializar();


	/**
	 * @param msgBundle the msgBundle to set
	 */
	public final void setMsgBundle(final ResourceBundle msgBundle)
	{
		AbstractMBean.msgBundle = msgBundle;
	}


	/**
	 * @return the sessionMBean
	 */
	public final SessionMBean getSessionMBean()
	{
		return sessionMBean;
	}

	/**
	 * @param sessionMBean the sessionMBean to set
	 */
	public final void setSessionMBean(final SessionMBean sessionMBean)
	{
		this.sessionMBean = sessionMBean;
	}



	/**
	 * Metodo para mostrar un mensaje INFO en pantalla.
	 * 
	 * @param infoMsgId
	 */
	public final void verMensajeInfo(final String infoMsgId)
	{
		String msg = infoMsgId;
		try
		{
			msg = msgBundle.getString(infoMsgId);
		}
		catch(Exception e)
		{
			msg = infoMsgId;
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Metodo para mostrar un mensaje de ERROR en pantalla.
	 * 
	 * @param errMsgId
	 */
	public final void verMensajeError(final String errMsgId)
	{
		String msg = errMsgId;
		try
		{
			msg = msgBundle.getString(errMsgId);
		}
		catch(Exception e)
		{
			msg = errMsgId;
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}


}
