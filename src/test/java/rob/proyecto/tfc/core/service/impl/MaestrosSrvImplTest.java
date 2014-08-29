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

import rob.proyecto.tfc.core.service.MaestrosService;
import rob.proyecto.tfc.data.entity.Categoria;
import rob.proyecto.tfc.data.entity.Especialidad;


/**
 * Test de la implementacion de servicio "CategoriasService".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class MaestrosSrvImplTest
{

	@Autowired
	@Qualifier("MaestrosService")
	private MaestrosService srv;

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
	public void testBuscarCategoriasTodas()
	{
		List<Categoria> liRetorno = srv.buscarCategoriasTodas();

		Assert.assertNotNull(liRetorno);
		Assert.assertFalse(liRetorno.isEmpty());
		Assert.assertEquals(5, liRetorno.size());
	}


	@Test
	public void testBuscarEspecialidadesTodas()
	{
		List<Especialidad> liRetorno = srv.buscarEspecialidadesTodas();

		Assert.assertNotNull(liRetorno);
		Assert.assertFalse(liRetorno.isEmpty());
		Assert.assertEquals(11, liRetorno.size());
	}


}
