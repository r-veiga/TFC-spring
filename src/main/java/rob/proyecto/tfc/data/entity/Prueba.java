package rob.proyecto.tfc.data.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the prueba database table.
 * 
 */
@Entity
@Table(name = "prueba")
@NamedQuery(name = "Prueba.findAll", query = "SELECT a FROM Prueba a")
public class Prueba implements Serializable
{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PruebaPK id;

	@Column(length = 500)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fecPrueba;

	@Column(length = 50, nullable = false)
	private String nombre;

	@Column(length = 200)
	private String archivo;



	// bi-directional many-to-one association to Paciente
	// @ManyToOne
	// @JoinColumn(name = "idPaciente", nullable = false, insertable = false, updatable = false)
	// private Paciente paciente;


	public Prueba() {}


	public PruebaPK getId()
	{
		return id;
	}

	public void setId(final PruebaPK id)
	{
		this.id = id;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(final String descripcion)
	{
		this.descripcion = descripcion;
	}

	public Date getFecPrueba()
	{
		return fecPrueba;
	}

	public void setFecPrueba(final Date fecPrueba)
	{
		this.fecPrueba = fecPrueba;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(final String nombre)
	{
		this.nombre = nombre;
	}

	public String getArchivo()
	{
		return archivo;
	}

	public void setArchivo(final String archivo)
	{
		this.archivo = archivo;
	}


	/*
		public Paciente getPaciente()
		{
			return paciente;
		}

		public void setPaciente(final Paciente paciente)
		{
			this.paciente = paciente;
		}
	*/

}
