package rob.proyecto.tfc.data.vo;


import java.io.Serializable;

import rob.proyecto.tfc.data.entity.Cita;


/**
 * VO con la informacion de la hora de una consulta para pedir cita.
 * 
 * @author user
 * 
 */
public class ConsultaHoraVO implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Hora de la consulta. */
	private String hora;

	/** Informacion de la cita de la consulta. */
	private Cita cita;

	/** Indicadoe para saber si la hora esta libre y se puede reservar. */
	private int libre = 1;


	/**
	 * @return the hora
	 */
	public final String getHora()
	{
		return hora;
	}


	/**
	 * @param hora the hora to set
	 */
	public final void setHora(final String hora)
	{
		this.hora = hora;
	}


	/**
	 * @return the cita
	 */
	public final Cita getCita()
	{
		return cita;
	}

	/**
	 * @param cita the cita to set
	 */
	public final void setCita(final Cita cita)
	{
		this.cita = cita;
	}


	/**
	 * @return the libre
	 */
	public int getLibre()
	{
		return libre;
	}


	/**
	 * @param libre the libre to set
	 */
	public final void setLibre(final int libre)
	{
		this.libre = libre;
	}


}
