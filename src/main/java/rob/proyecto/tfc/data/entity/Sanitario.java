package rob.proyecto.tfc.data.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sanitario database table.
 * 
 */
@Entity
@Table(name = "sanitario")
@NamedQueries({
	@NamedQuery(name = "Sanitario.findAll", query = "SELECT s FROM Sanitario s"),
	@NamedQuery(name = "Sanitario.findAllNoUser", query = "SELECT s FROM Sanitario s WHERE s.usuario IS NULL")
})
public class Sanitario implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 50)
	private String colegiado;

	@Column(length = 10)
	private String dni;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(length = 200)
	private String apellidos;

	@Temporal(TemporalType.DATE)
	private Date fecNacimiento;

	@Column(nullable = false, length = 50)
	private String categoria;

	@Column(nullable = false)
	private Long idEspecialidad;

	@Column(length = 10)
	private String tlfFijo;

	@Column(length = 10)
	private String tlfMovil;

	@Column(length = 250)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecAlta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecBaja;


	// bi-directional many-to-one association to Anotacion
	// @OneToMany(mappedBy = "sanitario")
	// private List<Anotacion> anotacions;


	// bi-directional one-to-one association to Usuario
	@OneToOne(mappedBy = "sanitario")
	private Usuario usuario;


	public Sanitario() {}


	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public String getColegiado()
	{
		return colegiado;
	}

	public void setColegiado(final String colegiado)
	{
		this.colegiado = colegiado;
	}

	public String getDni()
	{
		return dni;
	}

	public void setDni(final String dni)
	{
		this.dni = dni;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(final String nombre)
	{
		this.nombre = nombre;
	}

	public String getApellidos()
	{
		return apellidos;
	}

	public void setApellidos(final String apellidos)
	{
		this.apellidos = apellidos;
	}

	public Date getFecNacimiento()
	{
		return fecNacimiento;
	}

	public void setFecNacimiento(final Date fecNacimiento)
	{
		this.fecNacimiento = fecNacimiento;
	}

	public String getCategoria()
	{
		return categoria;
	}

	public void setCategoria(final String categoria)
	{
		this.categoria = categoria;
	}

	public Long getIdEspecialidad()
	{
		return idEspecialidad;
	}

	public void setIdEspecialidad(final Long idEspecialidad)
	{
		this.idEspecialidad = idEspecialidad;
	}

	public String getTlfFijo()
	{
		return tlfFijo;
	}

	public void setTlfFijo(final String tlfFijo)
	{
		this.tlfFijo = tlfFijo;
	}

	public String getTlfMovil()
	{
		return tlfMovil;
	}

	public void setTlfMovil(final String tlfMovil)
	{
		this.tlfMovil = tlfMovil;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public Date getFecAlta()
	{
		return fecAlta;
	}

	public void setFecAlta(final Date fecAlta)
	{
		this.fecAlta = fecAlta;
	}

	public Date getFecBaja()
	{
		return fecBaja;
	}

	public void setFecBaja(final Date fecBaja)
	{
		this.fecBaja = fecBaja;
	}




	/*	
		public List<Anotacion> getAnotacions()
		{
			return anotacions;
		}

		public void setAnotacions(final List<Anotacion> anotacions)
		{
			this.anotacions = anotacions;
		}

		public Anotacion addAnotacion(final Anotacion anotacion)
		{
			getAnotacions().add(anotacion);
			anotacion.setSanitario(this);

			return anotacion;
		}

		public Anotacion removeAnotacion(final Anotacion anotacion)
		{
			getAnotacions().remove(anotacion);
			anotacion.setSanitario(null);

			return anotacion;
		}
	*/


	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(final Usuario usuario)
	{
		this.usuario = usuario;
	}

}
