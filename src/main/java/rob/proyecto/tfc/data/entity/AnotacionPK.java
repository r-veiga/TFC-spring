package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The primary key class for the anotacion database table.
 * 
 */
@Embeddable
public class AnotacionPK implements Serializable
{

	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(unique = true, nullable = false)
	private java.util.Date fecha;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Long idSanitario;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Long idPaciente;


	public AnotacionPK() {}


	public java.util.Date getFecha()
	{
		return fecha;
	}

	public void setFecha(final java.util.Date fecha)
	{
		this.fecha = fecha;
	}

	public Long getIdSanitario()
	{
		return idSanitario;
	}

	public void setIdSanitario(final Long idSanitario)
	{
		this.idSanitario = idSanitario;
	}

	public Long getIdPaciente()
	{
		return idPaciente;
	}

	public void setIdPaciente(final Long idPaciente)
	{
		this.idPaciente = idPaciente;
	}

	@Override
	public boolean equals(final Object other)
	{
		if(this == other){ return true; }
		if(!(other instanceof AnotacionPK)){ return false; }
		AnotacionPK castOther = (AnotacionPK)other;
		return fecha.equals(castOther.fecha) && idSanitario == castOther.idSanitario
				&& idPaciente == castOther.idPaciente;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + fecha.hashCode();
		hash = hash * prime + idSanitario.intValue();
		hash = hash * prime + idPaciente.intValue();

		return hash;
	}
}
