package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the especialidad database table.
 * 
 */
@Entity
@Table(name = "especialidad")
@NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e")
public class Especialidad implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 50)
	private String nombre;


	// bi-directional many-to-one association to Cita
	// @OneToMany(mappedBy = "especialidad")
	// private List<Cita> citas;


	public Especialidad() {}


	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(final String nombre)
	{
		this.nombre = nombre;
	}


	/*
		public List<Cita> getCitas()
		{
			return citas;
		}

		public void setCitas(final List<Cita> citas)
		{
			this.citas = citas;
		}

		public Cita addCita(final Cita cita)
		{
			getCitas().add(cita);
			cita.setEspecialidad(this);

			return cita;
		}

		public Cita removeCita(final Cita cita)
		{
			getCitas().remove(cita);
			cita.setEspecialidad(null);

			return cita;
		}
	*/
}
