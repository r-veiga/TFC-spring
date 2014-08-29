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

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.PruebasService;
import rob.proyecto.tfc.data.entity.Prueba;
import rob.proyecto.tfc.data.entity.PruebaPK;


/**
 * Test de la implementacion de servicio "PruebasService".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class PruebasSrvImplTest
{

	@Autowired
	@Qualifier("PruebasService")
	private PruebasService srv;

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
	public void testBuscarPruebasdePaciente()
	{
		Long idPaciente = null;
		List<Prueba> liResultado = null;

		idPaciente = 0L;
		liResultado = srv.buscarPruebasdePaciente(idPaciente);

		Assert.assertNotNull(liResultado);
		Assert.assertTrue(liResultado.isEmpty());


		idPaciente = 1L;
		liResultado = srv.buscarPruebasdePaciente(idPaciente);

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
	}


	@Test
	public void testCrearPrueba() throws TFCException
	{
		Long idPaciente = 1L;
		List<Prueba> liRes1 = srv.buscarPruebasdePaciente(idPaciente);

		Assert.assertNotNull(liRes1);


		PruebaPK pk = new PruebaPK();
		pk.setIdPaciente(idPaciente);
		pk.setFecAlta(new Date());

		Prueba prueba = new Prueba();
		prueba.setId(pk);
		prueba.setFecPrueba(new Date());
		prueba.setNombre("Prueba " + new Date().toString());
		prueba.setDescripcion("Prueba " + new Date().toString());


		srv.crearPrueba(prueba);

		List<Prueba> liRes2 = srv.buscarPruebasdePaciente(idPaciente);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());
	}


	@Test
	public void testBorrarPrueba() throws TFCException
	{
		Long idPaciente = 1L;
		List<Prueba> liRes1 = srv.buscarPruebasdePaciente(idPaciente);

		Assert.assertNotNull(liRes1);


		PruebaPK pk = new PruebaPK();
		pk.setIdPaciente(idPaciente);
		pk.setFecAlta(new Date());

		Prueba prueba = new Prueba();
		prueba.setId(pk);
		prueba.setFecPrueba(new Date());
		prueba.setNombre("Prueba " + new Date().toString());
		prueba.setDescripcion("Prueba " + new Date().toString());


		srv.crearPrueba(prueba);

		List<Prueba> liRes2 = srv.buscarPruebasdePaciente(idPaciente);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());

		Prueba prueba2 = new Prueba();
		prueba2.setId(pk);
		srv.borrarPrueba(prueba2);

		List<Prueba> liRes3 = srv.buscarPruebasdePaciente(idPaciente);

		Assert.assertNotNull(liRes3);
		Assert.assertFalse(liRes3.isEmpty());
		Assert.assertEquals(liRes1.size(), liRes3.size());
	}


}
