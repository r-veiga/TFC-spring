package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the anotacion database table.
 * 
 */
@Entity
@Table(name = "anotacion")
@NamedQuery(name = "Anotacion.findAll", query = "SELECT a FROM Anotacion a")
public class Anotacion implements Serializable
{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AnotacionPK id;

	@Column(nullable = false, length = 500)
	private String texto;


	// bi-directional many-to-one association to Paciente
	// @ManyToOne
	// @JoinColumn(name = "idPaciente", nullable = false, insertable = false, updatable = false)
	// private Paciente paciente;


	// bi-directional many-to-one association to Sanitario
	@ManyToOne
	@JoinColumn(name = "idSanitario", nullable = false, insertable = false, updatable = false)
	private Sanitario sanitario;


	public Anotacion() {}


	public AnotacionPK getId()
	{
		return id;
	}

	public void setId(final AnotacionPK id)
	{
		this.id = id;
	}

	public String getTexto()
	{
		return texto;
	}

	public void setTexto(final String texto)
	{
		this.texto = texto;
	}


	/*
		public Paciente getPaciente()
		{
			return this.paciente;
		}

		public void setPaciente(Paciente paciente)
		{
			this.paciente = paciente;
		}
	*/


	public Sanitario getSanitario()
	{
		return sanitario;
	}

	public void setSanitario(final Sanitario sanitario)
	{
		this.sanitario = sanitario;
	}

}
