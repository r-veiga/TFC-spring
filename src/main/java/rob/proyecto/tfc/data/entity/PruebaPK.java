package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The primary key class for the prueba database table.
 * 
 */
@Embeddable
public class PruebaPK implements Serializable
{

	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Long idPaciente;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(unique = true, nullable = false)
	private java.util.Date fecAlta;


	public PruebaPK() {}


	public Long getIdPaciente()
	{
		return idPaciente;
	}

	public void setIdPaciente(final Long idPaciente)
	{
		this.idPaciente = idPaciente;
	}

	public java.util.Date getFecAlta()
	{
		return fecAlta;
	}

	public void setFecAlta(final java.util.Date fecAlta)
	{
		this.fecAlta = fecAlta;
	}

	@Override
	public boolean equals(final Object other)
	{
		if(this == other){ return true; }
		if(!(other instanceof PruebaPK)){ return false; }
		PruebaPK castOther = (PruebaPK)other;
		return idPaciente == castOther.idPaciente && fecAlta.equals(castOther.fecAlta);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + idPaciente.intValue();
		hash = hash * prime + fecAlta.hashCode();

		return hash;
	}

}
