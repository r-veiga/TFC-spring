package rob.proyecto.tfc.core.service.impl;


import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rob.proyecto.tfc.core.service.UbicacionesService;
import rob.proyecto.tfc.data.entity.Ubicacion;


/**
 * Test de la implementacion de servicio "UbicacionesDAO".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class UbicacionesSrvImplTest
{

	@Autowired
	@Qualifier("UbicacionesService")
	private UbicacionesService srv;

	private Ubicacion entidad = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		entidad = new Ubicacion();
		entidad.setId(10L);
	}


	@Test
	public final void testServicio()
	{
		Assert.assertNotNull(srv);
	}


	@Test
	public final void testBuscarTodos()
	{
		List<Ubicacion> liResultado = srv.buscarTodas();

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
	}


	@Test
	public final void testBuscarTodasLibres()
	{
		List<Ubicacion> liResultado = srv.buscarTodasLibres();

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
	}
}
