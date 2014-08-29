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
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.core.exception.TFCSQLException;
import rob.proyecto.tfc.data.entity.Usuario;


/**
 * Test de la implementacion de servicio "UsuariosDAO".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class UsuariosDAOImplTest
{

	@Autowired
	@Qualifier("UsuariosDAO")
	private UsuariosDAOImpl dao;

	private Usuario entidad = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		entidad = new Usuario();
		entidad.setId(0L);
		entidad.setUser("admin");
		entidad.setPass("admin");
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
		Usuario resultado = dao.buscarPK(entidad, entidad.getId());

		Assert.assertNotNull(resultado);
		Assert.assertEquals(entidad.getId(), resultado.getId());
	}


	@Test
	@Transactional
	public final void testbuscarAcceso() throws TFCSQLException
	{
		Usuario usrAcc = dao.buscarAcceso(entidad);

		Assert.assertNotNull(usrAcc);
		Assert.assertEquals(entidad.getId(), usrAcc.getId());
		Assert.assertNotEquals(entidad.getFecAcceso(), usrAcc.getFecAcceso());

		Usuario usrFind = dao.buscarPK(entidad, entidad.getId());

		Assert.assertNotNull(usrFind);
		Assert.assertEquals(entidad.getId(), usrFind.getId());
		Assert.assertNotEquals(entidad.getFecAcceso(), usrFind.getFecAcceso());

		Assert.assertEquals(usrAcc.getFecAcceso(), usrFind.getFecAcceso());
	}
}
