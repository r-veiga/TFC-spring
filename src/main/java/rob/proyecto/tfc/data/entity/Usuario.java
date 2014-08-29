package rob.proyecto.tfc.data.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
		@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findBy_UsrPas", query = "SELECT u FROM Usuario u WHERE u.user=:user and u.pass=:pass and u.fecBaja IS NULL") })
public class Usuario implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 50)
	private String tipo;

	@Column(nullable = false, length = 8)
	private String user;

	@Column(nullable = false, length = 32)
	private String pass;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecAcceso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date fecAlta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecBaja;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(length = 200)
	private String apellidos;

	@Column(length = 200)
	private String email;


	// bi-directional one-to-one association to Sanitario
	@OneToOne
	@JoinColumn(name = "idSanitario")
	private Sanitario sanitario;

	public Usuario() {}


	/**
	 * @return the id
	 */
	public final Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(final Long id)
	{
		this.id = id;
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
	 * @return the user
	 */
	public final String getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public final void setUser(final String user)
	{
		this.user = user;
	}


	/**
	 * @return the pass
	 */
	public final String getPass()
	{
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public final void setPass(final String pass)
	{
		this.pass = pass;
	}


	/**
	 * @return the fecAcceso
	 */
	public final Date getFecAcceso()
	{
		return fecAcceso;
	}

	/**
	 * @param fecAcceso the fecAcceso to set
	 */
	public final void setFecAcceso(final Date fecAcceso)
	{
		this.fecAcceso = fecAcceso;
	}


	/**
	 * @return the fecAlta
	 */
	public final Date getFecAlta()
	{
		return fecAlta;
	}

	/**
	 * @param fecAlta the fecAlta to set
	 */
	public final void setFecAlta(final Date fecAlta)
	{
		this.fecAlta = fecAlta;
	}


	/**
	 * @return the fecBaja
	 */
	public final Date getFecBaja()
	{
		return fecBaja;
	}

	/**
	 * @param fecBaja the fecBaja to set
	 */
	public final void setFecBaja(final Date fecBaja)
	{
		this.fecBaja = fecBaja;
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
	 * @return the apellidos
	 */
	public final String getApellidos()
	{
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public final void setApellidos(final String apellidos)
	{
		this.apellidos = apellidos;
	}


	/**
	 * @return the email
	 */
	public final String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public final void setEmail(final String email)
	{
		this.email = email;
	}


	/**
	 * @return the sanitario
	 */
	public final Sanitario getSanitario()
	{
		return sanitario;
	}

	/**
	 * @param sanitario the sanitario to set
	 */
	public final void setSanitario(final Sanitario sanitario)
	{
		this.sanitario = sanitario;
	}
}
