package rob.proyecto.tfc.core.service.impl;


import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rob.proyecto.tfc.TFCConstantes.TIPO_SANITARIO;
import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.AnotacionesService;
import rob.proyecto.tfc.data.entity.Anotacion;
import rob.proyecto.tfc.data.entity.AnotacionPK;


/**
 * Test de la implementacion de servicio "AnotacionesService".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class AnotacionesSrvImplTest
{

	@Autowired
	@Qualifier("AnotacionesService")
	private AnotacionesService srv;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{}


	@Test
	public final void testServicio()
	{
		Assert.assertNotNull(srv);
	}


	@Test
	public final void buscarNotasPacientePorEditor()
	{
		buscarNotasPacientePorEditor_Aux(1L, TIPO_SANITARIO.MEDICO);

		buscarNotasPacientePorEditor_Aux(1L, TIPO_SANITARIO.ENFERMERIA);

		buscarNotasPacientePorEditor_Aux(1L, TIPO_SANITARIO.AUXILIAR);

		buscarNotasPacientePorEditor_Aux(1L, TIPO_SANITARIO.REHABILITACION);

		buscarNotasPacientePorEditor_Aux(1L, TIPO_SANITARIO.TRABAJO_SOCIAL);
	}


	private final void buscarNotasPacientePorEditor_Aux(final Long idPaciente, final TIPO_SANITARIO tipoSan)
	{
		List<Anotacion> liResultado = srv.buscarNotasPacientePorEditor(idPaciente, tipoSan);

		Assert.assertNotNull("Busqueda por: " + tipoSan.toString(), liResultado);
		Assert.assertFalse("Busqueda por: " + tipoSan.toString(), liResultado.isEmpty());
	}


	@Test
	public void testCrearAnotacion() throws TFCException
	{
		// -- Buscar registros actuales.
		List<Anotacion> liPreAlta = srv.buscarNotasPacientePorEditor(1L, TIPO_SANITARIO.MEDICO);

		Assert.assertNotNull("Busqueda pre ALTA:", liPreAlta);
		Assert.assertFalse("Busqueda pre ALTA:", liPreAlta.isEmpty());


		// -- Crear nuevo registro.
		Date fecPrueba = new Date();

		AnotacionPK id = new AnotacionPK();
		id.setFecha(fecPrueba);
		id.setIdPaciente(1L);
		id.setIdSanitario(1L);

		Anotacion entNueva = new Anotacion();
		entNueva.setId(id);
		entNueva.setTexto("Texto de prueba " + fecPrueba);


		Anotacion resultado = srv.crearAnotacion(entNueva);

		Assert.assertNotNull(resultado);


		// -- Buscar registros actualiados.
		List<Anotacion> liPostAlta = srv.buscarNotasPacientePorEditor(1L, TIPO_SANITARIO.MEDICO);

		Assert.assertNotNull("Busqueda post ALTA:", liPostAlta);
		Assert.assertFalse("Busqueda post ALTA:", liPostAlta.isEmpty());
		Assert.assertTrue("Busqueda post ALTA:", liPostAlta.size() == liPreAlta.size() + 1);
	}
}
