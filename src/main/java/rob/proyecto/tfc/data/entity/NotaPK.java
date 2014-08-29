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
public class NotaPK implements Serializable
{

	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Long idSanitario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(unique = true, nullable = false)
	private java.util.Date fecha;


	public NotaPK() {}


	public Long getIdSanitario()
	{
		return idSanitario;
	}

	public void setIdSanitario(final Long idSanitario)
	{
		this.idSanitario = idSanitario;
	}

	public java.util.Date getFecha()
	{
		return fecha;
	}

	public void setFecha(final java.util.Date fecha)
	{
		this.fecha = fecha;
	}

	@Override
	public boolean equals(final Object other)
	{
		if(this == other){ return true; }
		if(!(other instanceof NotaPK)){ return false; }
		NotaPK castOther = (NotaPK)other;
		return idSanitario == castOther.idSanitario && fecha.equals(castOther.fecha);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + idSanitario.intValue();
		hash = hash * prime + fecha.hashCode();

		return hash;
	}

}
