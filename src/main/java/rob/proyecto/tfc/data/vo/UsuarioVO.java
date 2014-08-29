package rob.proyecto.tfc.data.vo;


import java.io.Serializable;
import java.util.Date;


/**
 * VO con la informacion del usuario.
 * 
 * @author user
 * 
 */
public class UsuarioVO implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	//-- Datos de la entidad Usuario

	/** ID Usuario. */
	private Long idUsuario;

	/** Tipo de usuario. */
	private String tipo;

	/** Nombre y apellidos del usuario. */
	private String nombre;

	/** Fecha del ultimo acceso. */
	private Date ultimoAcceso;


	//-- Si el usuario es ademas sanitario se rellenan estos campos:

	//-- Datos de la entidad Sanitarios.

	/** ID personal sanitario. */
	private Long idSanitario;

	/** Categoria sanitaria. */
	private String categoria;

	/** iD Especialidad sanitaria. */
	private Long idEspecialidad;


	//-- Datos de la entidad Usuario

	/**
	 * @return the idUsuario
	 */
	public final Long getIdUsuario()
	{
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public final void setIdUsuario(final Long idUsuario)
	{
		this.idUsuario = idUsuario;
	}


	/**
	 * @return the tipo
	 */
	public final String getTipo()
	{
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public final void setTipo(final String tipo)
	{
		this.tipo = tipo;
	}


	/**
	 * @return the nombre
	 */
	public final String getNombre()
	{
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public final void setNombre(final String nombre)
	{
		this.nombre = nombre;
	}


	/**
	 * @return the ultimoAcceso
	 */
	public final Date getUltimoAcceso()
	{
		return ultimoAcceso;
	}

	/**
	 * @param ultimoAcceso the ultimoAcceso to set
	 */
	public final void setUltimoAcceso(final Date ultimoAcceso)
	{
		this.ultimoAcceso = ultimoAcceso;
	}



	//-- Datos de la entidad Sanitarios.

	/**
	 * @return the idSanitario
	 */
	public final Long getIdSanitario()
	{
		return idSanitario;
	}

	/**
	 * @param idSanitario the idSanitario to set
	 */
	public final void setIdSanitario(final Long idSanitario)
	{
		this.idSanitario = idSanitario;
	}


	/**
	 * @return the categoria
	 */
	public final String getCategoria()
	{
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public final void setCategoria(final String categoria)
	{
		this.categoria = categoria;
	}


	/**
	 * @return the idEspecialidad
	 */
	public final Long getIdEspecialidad()
	{
		return idEspecialidad;
	}

	/**
	 * @param idEspecialidad the idEspecialidad to set
	 */
	public final void setIdEspecialidad(final Long idEspecialidad)
	{
		this.idEspecialidad = idEspecialidad;
	}

}
