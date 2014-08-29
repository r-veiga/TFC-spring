package rob.proyecto.tfc.core.dao.impl;


import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rob.proyecto.tfc.data.entity.Prueba;
import rob.proyecto.tfc.data.entity.PruebaPK;


/**
 * Test de la implementacion de servicio "PruebasDAO".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class PruebasDAOImplTest
{

	@Autowired
	@Qualifier("PruebasDAO")
	private PruebasDAOImpl dao;

	private Prueba entidad = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		entidad = new Prueba();

		PruebaPK pk = new PruebaPK();
		pk.setFecAlta(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-01-01 01:00:00"));
		pk.setIdPaciente(1L);

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
		List<?> liResultado = dao.buscarAll();

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
	}


	@Test
	public final void testBuscarPK()
	{
		Prueba resultado = dao.buscarPK(entidad, entidad.getId());

		Assert.assertNotNull(resultado);
		Assert.assertEquals(entidad.getId(), resultado.getId());
	}


}
