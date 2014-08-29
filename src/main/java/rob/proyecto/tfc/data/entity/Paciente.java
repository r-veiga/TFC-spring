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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name = "paciente")
@NamedQueries({ @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p") })
public class Paciente implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 200)
	private String apellidos;

	@Column(length = 100)
	private String codPostal;

	@Column(length = 10)
	private String dni;

	@Column(length = 250)
	private String direccion;

	@Column(length = 250)
	private String email;

	@Column(nullable = false, length = 20)
	private String expediente;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date fecAlta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecBaja;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecIngreso;

	@Temporal(TemporalType.DATE)
	private Date fecNacimiento;

	@Column(nullable = true)
	private Long idUbicacion;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(length = 100)
	private String pais;

	@Column(length = 100)
	private String provincia;

	@Column(length = 10)
	private String tlfFijo;

	@Column(length = 10)
	private String tlfMovil;

	@Transient
	private Boolean hospitalizado = null;

	public Boolean isHospitalizado()
	{
		return hospitalizado;
	}

	public void setHospitalizado(final Boolean hospitalizado)
	{
		this.hospitalizado = hospitalizado;
	}


	// bi-directional many-to-one association to Anotacion
	// @OneToMany(mappedBy = "paciente")
	// private List<Anotacion> anotaciones;


	// bi-directional many-to-one association to Prueba
	// @OneToMany(mappedBy = "paciente")
	// private List<Prueba> pruebas;


	// bi-directional many-to-one association to Cita
	// @OneToMany(mappedBy = "paciente")
	// private List<Cita> citas;


	public Paciente() {}


	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public String getApellidos()
	{
		return apellidos;
	}

	public void setApellidos(final String apellidos)
	{
		this.apellidos = apellidos;
	}

	public String getCodPostal()
	{
		return codPostal;
	}

	public void setCodPostal(final String codPostal)
	{
		this.codPostal = codPostal;
	}

	public String getDni()
	{
		return dni;
	}

	public void setDni(final String dni)
	{
		this.dni = dni;
	}

	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(final String direccion)
	{
		this.direccion = direccion;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getExpediente()
	{
		return expediente;
	}

	public void setExpediente(final String expediente)
	{
		this.expediente = expediente;
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

	public Date getFecIngreso()
	{
		return fecIngreso;
	}

	public void setFecIngreso(final Date fecIngreso)
	{
		this.fecIngreso = fecIngreso;
	}

	public Date getFecNacimiento()
	{
		return fecNacimiento;
	}

	public void setFecNacimiento(final Date fecNacimiento)
	{
		this.fecNacimiento = fecNacimiento;
	}


	public Long getIdUbicacion()
	{
		return idUbicacion;
	}

	public void setIdUbicacion(final Long idUbicacion)
	{
		this.idUbicacion = idUbicacion;
	}


	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(final String nombre)
	{
		this.nombre = nombre;
	}


	public String getPais()
	{
		return pais;
	}

	public void setPais(final String pais)
	{
		this.pais = pais;
	}

	public String getProvincia()
	{
		return provincia;
	}

	public void setProvincia(final String provincia)
	{
		this.provincia = provincia;
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

	/* public List<Anotacion> getAnotaciones() { return anotaciones; }
	 * 
	 * public void setAnotaciones(final List<Anotacion> anotaciones) { this.anotaciones = anotaciones; }
	 * 
	 * public Anotacion addAnotacion(final Anotacion anotacion) { getAnotaciones().add(anotacion);
	 * anotacion.setPaciente(this);
	 * 
	 * return anotacion; }
	 * 
	 * public Anotacion removeAnotacion(final Anotacion anotacion) { getAnotaciones().remove(anotacion);
	 * anotacion.setPaciente(null);
	 * 
	 * return anotacion; }
	 * 
	 * public List<Prueba> getPruebas() { return pruebas; }
	 * 
	 * public void setPruebas(final List<Prueba> pruebas) { this.pruebas = pruebas; }
	 * 
	 * public Prueba addPrueba(final Prueba prueba) { getPruebas().add(prueba); prueba.setPaciente(this);
	 * 
	 * return prueba; }
	 * 
	 * public Prueba removePrueba(final Prueba prueba) { getPruebas().remove(prueba); prueba.setPaciente(null);
	 * 
	 * return prueba; }
	 * 
	 * public List<Cita> getCitas() { return citas; }
	 * 
	 * public void setCitas(final List<Cita> citas) { this.citas = citas; }
	 * 
	 * public Cita addCita(final Cita cita) { getCitas().add(cita); cita.setPaciente(this);
	 * 
	 * return cita; }
	 * 
	 * public Cita removeCita(final Cita cita) { getCitas().remove(cita); cita.setPaciente(null);
	 * 
	 * return cita; } */
}
