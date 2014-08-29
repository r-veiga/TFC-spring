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
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.UsuariosService;
import rob.proyecto.tfc.data.entity.Sanitario;
import rob.proyecto.tfc.data.entity.Usuario;
import rob.proyecto.tfc.data.vo.UsuarioVO;


/**
 * Test de la implementacion de servicio "UsuariosService".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class UsuariosSrvImplTest
{

	@Autowired
	@Qualifier("UsuariosService")
	private UsuariosService srv;

	private Usuario entidad;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		entidad = new Usuario();
	}


	@Test
	public final void testServicio()
	{
		Assert.assertNotNull(srv);
	}


	@Test
	public final void testValidarUsuario_0L() throws TFCException
	{
		Usuario usr = new Usuario();
		usr.setUser("admin");
		usr.setPass("admin");

		UsuarioVO retorno = srv.validarUsuario(usr);

		Assert.assertNotNull(retorno);
		Assert.assertEquals(0L, retorno.getIdUsuario().longValue());
	}


	@Test
	public final void testValidarUsuario_1L() throws TFCException
	{
		Usuario usr = new Usuario();
		usr.setUser("med1");
		usr.setPass("med1");

		UsuarioVO retorno = srv.validarUsuario(usr);

		Assert.assertNotNull(retorno);
		Assert.assertEquals(1L, retorno.getIdUsuario().longValue());
	}



	@Test
	public void testBuscarUsuario()
	{
		Usuario usr = null;

		usr = srv.buscarUsuario(100L);

		Assert.assertNull(usr);


		usr = srv.buscarUsuario(1L);

		Assert.assertNotNull(usr);
		Assert.assertTrue(1L == usr.getId());
		Assert.assertNotNull(usr.getSanitario());
		Assert.assertEquals("u_Med1_n", usr.getNombre());
	}


	@Test
	public void testBuscarUsuarios()
	{
		entidad.setNombre("u_Aux1_n");

		List<Usuario> liRes = srv.buscarUsuarios(entidad);

		Assert.assertNotNull(liRes);
		Assert.assertFalse(liRes.isEmpty());
		Assert.assertEquals(1, liRes.size());

		entidad = new Usuario();
		entidad.setTipo("ADMINISTRATIVO");

		liRes = srv.buscarUsuarios(entidad);

		Assert.assertNotNull(liRes);
		Assert.assertFalse(liRes.isEmpty());
		Assert.assertEquals(1, liRes.size());
	}


	@Test
	public void testBuscarSanitariosSinUsuario()
	{
		List<Sanitario> liRes = srv.buscarSanitariosSinUsuario();

		Assert.assertNotNull(liRes);
		Assert.assertTrue(liRes.isEmpty());
	}


	@Test
	@Transactional
	public void testCrearUsuario() throws TFCException
	{
		List<Usuario> liRes1 = srv.buscarUsuarios(null);

		Assert.assertNotNull(liRes1);


		Usuario usr = new Usuario();

		usr.setUser("x");
		usr.setPass("x");
		usr.setNombre("X");
		usr.setApellidos("X");
		usr.setSanitario(null);

		srv.crearUsuario(usr);


		List<Usuario> liRes2 = srv.buscarUsuarios(null);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());
	}


	@Test
	@Transactional
	public void testModificarUsuario_UPD() throws TFCException
	{
		Usuario usr1 = srv.buscarUsuario(1L);

		Assert.assertNotNull(usr1);


		usr1.setNombre("X");
		srv.modificarUsuario(usr1);


		Usuario usr2 = srv.buscarUsuario(1L);

		Assert.assertNotNull(usr2);
		Assert.assertEquals("X", usr2.getNombre());
	}

}
