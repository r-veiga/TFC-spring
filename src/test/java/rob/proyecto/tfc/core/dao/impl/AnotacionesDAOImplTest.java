package rob.proyecto.tfc.core.dao.impl;


import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.TFCConstantes.TIPO_SANITARIO;
import rob.proyecto.tfc.core.exception.TFCSQLException;
import rob.proyecto.tfc.data.entity.Anotacion;
import rob.proyecto.tfc.data.entity.AnotacionPK;


/**
 * Test de la implementacion de servicio "AnotacionesDAO".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class AnotacionesDAOImplTest
{

	@Autowired
	@Qualifier("AnotacionesDAO")
	private AnotacionesDAOImpl dao;

	private Anotacion entidad = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		entidad = new Anotacion();

		AnotacionPK pk = new AnotacionPK();
		pk.setFecha(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-01-01 01:00:00"));
		pk.setIdPaciente(1L);
		pk.setIdSanitario(1L);

		entidad.setId(pk);
	}


	@Test
	public final void testDAO()
	{
		Assert.assertNotNull(dao);
	}


	@Test
	public final void testBuscarTodos()
	{
		List<Anotacion> liResultado = dao.buscarAll();

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
	}


	@Test
	public final void testBuscarPK()
	{
		Anotacion resultado = dao.buscarPK(entidad, entidad.getId());

		Assert.assertNotNull(resultado);
		Assert.assertEquals(entidad.getId(), resultado.getId());
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
		List<Anotacion> liResultado = dao.buscarNotasPacientePorEditor(idPaciente, tipoSan);

		Assert.assertNotNull("Busqueda por: " + tipoSan.toString(), liResultado);
		Assert.assertFalse("Busqueda por: " + tipoSan.toString(), liResultado.isEmpty());
	}



	@Test
	@Transactional
	public void testCrear() throws TFCSQLException
	{
		// -- Buscar registros actuales.
		List<Anotacion> liPreAlta = dao.buscarAll();

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

		Anotacion resultado = dao.crear(entNueva);

		Assert.assertNotNull(resultado);


		// -- Buscar registros actualiados.
		List<Anotacion> liPostAlta = dao.buscarAll();

		Assert.assertNotNull("Busqueda post ALTA:", liPostAlta);
		Assert.assertFalse("Busqueda post ALTA:", liPostAlta.isEmpty());
		Assert.assertTrue("Busqueda post ALTA:", liPostAlta.size() == liPreAlta.size() + 1);

	}


}
