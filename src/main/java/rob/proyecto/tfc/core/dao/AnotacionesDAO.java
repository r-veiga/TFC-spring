package rob.proyecto.tfc.core.dao;


import java.util.List;

import rob.proyecto.tfc.TFCConstantes;
import rob.proyecto.tfc.data.entity.Anotacion;


/**
 * Interfaz de metodos de acceso a datos de la entidad "Anotacion".
 * 
 * @author user
 * 
 */
public interface AnotacionesDAO extends AbstractDAO<Anotacion>
{

	/**
	 * Metodo para obtener solo las anotacines de un tipo de sanitario para un paciente.
	 * 
	 * @param idPaciente
	 * @param tipoSanitario
	 * @return
	 */
	List<Anotacion> buscarNotasPacientePorEditor(Long idPaciente, TFCConstantes.TIPO_SANITARIO tipoSanitario);

}
