package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The primary key class for the cita database table.
 * 
 */
@Embeddable
public class CitaPK implements Serializable
{

	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(unique = true, nullable = false)
	private java.util.Date fecha;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Long idPaciente;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Long idEspecialidad;


	public CitaPK() {}


	public java.util.Date getFecha()
	{
		return fecha;
	}

	public void setFecha(final java.util.Date fecha)
	{
		this.fecha = fecha;
	}

	public Long getIdPaciente()
	{
		return idPaciente;
	}

	public void setIdPaciente(final Long idPaciente)
	{
		this.idPaciente = idPaciente;
	}

	public Long getIdEspecialidad()
	{
		return idEspecialidad;
	}

	public void setIdEspecialidad(final Long idEspecialidad)
	{
		this.idEspecialidad = idEspecialidad;
	}

	@Override
	public boolean equals(final Object other)
	{
		if(this == other){ return true; }
		if(!(other instanceof CitaPK)){ return false; }
		CitaPK castOther = (CitaPK)other;
		return fecha.equals(castOther.fecha) && idPaciente == castOther.idPaciente.intValue()
				&& idEspecialidad == castOther.idEspecialidad.intValue();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + fecha.hashCode();
		hash = hash * prime + idPaciente.intValue();
		hash = hash * prime + idEspecialidad.intValue();

		return hash;
	}
}
