package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name = "categoria")
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
public class Categoria implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, length = 50)
	private String id;

	@Column(nullable = false, length = 50)
	private String nombre;


	public Categoria() {}


	public String getId()
	{
		return id;
	}

	public void setId(final String id)
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

}
