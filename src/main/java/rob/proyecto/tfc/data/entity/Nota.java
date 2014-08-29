package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the nota database table.
 * 
 */
@Entity
@Table(name = "nota")
@NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n")
public class Nota implements Serializable
{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NotaPK id;

	@Column(length = 500)
	private String descripcion;

	public Nota() {}


	public NotaPK getId()
	{
		return id;
	}

	public void setId(final NotaPK id)
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

}
