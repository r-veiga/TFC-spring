package rob.proyecto.tfc.web.jsf.mbean;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rob.proyecto.tfc.core.service.MaestrosService;
import rob.proyecto.tfc.data.entity.Categoria;
import rob.proyecto.tfc.data.entity.Especialidad;


/**
 * ManageBean para la gestion de elementos a nivel de contexto de aplicacion.
 * 
 * @author user
 * 
 */
@ManagedBean(name = "ApplicationMBean", eager = true)
@ApplicationScoped
public class ApplicationMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationMBean.class);


	/** Servicio spring de gestion de entidades maestras. */
	@ManagedProperty("#{MaestrosService}")
	private static MaestrosService maestrosSrv;


	/** Lista de categorias de la aplicacion */
	private static List<Categoria> categorias;

	/** Lista de especialidades de la aplicacion */
	private static List<Especialidad> especialidades;

	/** Instancia del objeto Calendar para manejar fechas. */
	private static Calendar calenda = Calendar.getInstance();


	//---------- Carga del servicio Spring.


	/**
	 * @param maestrosSrv the maestrosSrv to set
	 */
	public final void setMaestrosSrv(final MaestrosService maestrosSrv)
	{
		ApplicationMBean.maestrosSrv = maestrosSrv;
	}


	//---------- Inicializacion del MBean.


	/**
	 * Metodo para la inicializacion del MBean de forma automatica tras su crreacion.
	 */
	@PostConstruct
	private void inicializar()
	{
		LOG.info("Inicializando MBean");

		categorias = maestrosSrv.buscarCategoriasTodas();

		especialidades = maestrosSrv.buscarEspecialidadesTodas();
	}


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * Metodo para obtener la lista de categorias de personal sanitario del sistema.
	 * 
	 * @return the categorias
	 */
	public final List<Categoria> getCategorias()
	{
		return categorias;
	}

	/**
	 * Metodo para obtener el nombre de uan categoria a partir de su id.
	 * 
	 * @param idCategoria
	 * @return
	 */
	public final String getNombreCategoria(final String idCategoria)
	{
		String nombre = "";

		for(int i = 0; i < categorias.size(); i++)
		{
			if(categorias.get(i).getId().equals(idCategoria))
			{
				nombre = categorias.get(i).getNombre();
				i = categorias.size();
			}
		}

		return nombre;
	}


	/**
	 * Metodo para obtener la lista de especialidaes medicas del sistema.
	 * 
	 * @return the especialidades
	 */
	public final List<Especialidad> getEspecialidades()
	{
		return especialidades;
	}



	/**
	 * Metodo para devolver un objeto Date con la fecha y hora actual.
	 * - Principalmente se utiliza en los campos p:calendar para delimitar la fecha minima.
	 * 
	 * @return
	 */
	public Date getFechaToday()
	{
		return new Date();
	}


	/**
	 * Metodo para devolver un objeto Date con la fecha y hora actual +1 dia.
	 * - Principalmente se utiliza en los campos p:calendar para delimitar la fecha minima.
	 * 
	 * @return
	 */
	public Date getFechaTomorrow()
	{
		calenda.clear();

		calenda.setTime(new Date());
		calenda.add(Calendar.DAY_OF_MONTH, 1);

		return calenda.getTime();
	}

}
