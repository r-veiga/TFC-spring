package rob.proyecto.tfc.core.service;


import java.util.List;

import rob.proyecto.tfc.data.entity.Categoria;
import rob.proyecto.tfc.data.entity.Especialidad;


/**
 * Intefaz de metodos de logica de gertion de entidades Maestras.
 * 
 * @author user
 * 
 */
public interface MaestrosService
{

	/**
	 * Metodo para obtener todas las Categorias.
	 * 
	 * @return
	 */
	List<Categoria> buscarCategoriasTodas();


	/**
	 * Metodo para obtener todas las Especialidades.
	 * 
	 * @return
	 */
	List<Especialidad> buscarEspecialidadesTodas();

}
