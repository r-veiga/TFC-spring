package rob.proyecto.tfc.core.dao.impl;


import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rob.proyecto.tfc.data.entity.Categoria;


/**
 * Test de la implementacion de servicio "CategoriasDAO".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class CategoriasDAOImplTest
{

	@Autowired
	@Qualifier("CategoriasDAO")
	private CategoriasDAOImpl dao;

	private Categoria entidad = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		entidad = new Categoria();
		entidad.setId("MEDICO");
	}


	@Test
	public final void testDAO()
	{
		Assert.assertNotNull(dao);
	}


	@Test
	public final void testBuscarTodos()
	{
		List<?> liResultado = dao.buscarAll();

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
	}


	@Test
	public final void testBuscarPK()
	{
		Categoria resultado = dao.buscarPK(entidad, entidad.getId());

		Assert.assertNotNull(resultado);
		Assert.assertEquals(entidad.getId(), resultado.getId());
	}


}
