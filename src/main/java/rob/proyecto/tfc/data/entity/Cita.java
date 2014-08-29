package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cita database table.
 * 
 */
@Entity
@Table(name = "cita")
@NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
public class Cita implements Serializable
{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CitaPK id;

	// bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name = "idEspecialidad", nullable = false, insertable = false, updatable = false)
	private Especialidad especialidad;


	// bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name = "idPaciente", nullable = false, insertable = false, updatable = false)
	private Paciente paciente;


	public Cita() {}


	public CitaPK getId()
	{
		return id;
	}

	public void setId(final CitaPK id)
	{
		this.id = id;
	}

	public Especialidad getEspecialidad()
	{
		return especialidad;
	}

	public void setEspecialidad(final Especialidad especialidad)
	{
		this.especialidad = especialidad;
	}


	public Paciente getPaciente()
	{
		return paciente;
	}

	public void setPaciente(final Paciente paciente)
	{
		this.paciente = paciente;
	}

}
