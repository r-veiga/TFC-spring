package rob.proyecto.tfc.data.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ubicacion database table.
 * 
 */
@Entity
@Table(name = "ubicacion")
@NamedQueries({
		@NamedQuery(name = "Ubicacion.findAll", query = "SELECT u FROM Ubicacion u"),
		@NamedQuery(name = "Ubicacion.findAllVacancy", query = "SELECT u FROM Ubicacion u WHERE u.id NOT IN (SELECT p.idUbicacion FROM Paciente p WHERE p.idUbicacion IS NOT NULL)") })
public class Ubicacion implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 50)
	private String nombre;


	public Ubicacion() {}


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

}
