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
import org.springframework.transaction.annotation.Transactional;

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.SanitariosService;
import rob.proyecto.tfc.data.entity.Nota;
import rob.proyecto.tfc.data.entity.NotaPK;
import rob.proyecto.tfc.data.entity.Sanitario;


/**
 * Test de la implementacion de servicio "SanitariosService".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class SanitariosSrvImplTest
{

	@Autowired
	@Qualifier("SanitariosService")
	private SanitariosService srv;

	private Sanitario entidad;

	private Long idSanitario;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		idSanitario = 1L;

		entidad = new Sanitario();
	}


	@Test
	public final void testServicio()
	{
		Assert.assertNotNull(srv);
	}



	//-- Metodos de gestion de Sanitario

	@Test
	public void testBuscarSanitario()
	{
		Sanitario san = null;

		san = srv.buscarSanitario(100L);

		Assert.assertNull(san);


		san = srv.buscarSanitario(1L);

		Assert.assertNotNull(san);
		Assert.assertTrue(1L == san.getId());
		Assert.assertTrue(1L == san.getIdEspecialidad());
		Assert.assertEquals("M1", san.getNombre());
	}


	@Test
	public void testBuscarSanitarios()
	{
		entidad.setColegiado("C0001");

		List<Sanitario> liRes = srv.buscarSanitarios(entidad);

		Assert.assertNotNull(liRes);
		Assert.assertFalse(liRes.isEmpty());
		Assert.assertEquals(1, liRes.size());


		entidad = new Sanitario();
		entidad.setIdEspecialidad(2L);

		liRes = srv.buscarSanitarios(entidad);

		Assert.assertNotNull(liRes);
		Assert.assertFalse(liRes.isEmpty());
		Assert.assertEquals(1, liRes.size());
	}


	@Test
	@Transactional
	public void testCrearSanitario() throws TFCException
	{
		List<Sanitario> liRes1 = srv.buscarSanitarios(null);

		Assert.assertNotNull(liRes1);


		Sanitario san = new Sanitario();

		san.setColegiado("X");
		san.setNombre("X");
		san.setApellidos("X");
		san.setCategoria("X");
		san.setIdEspecialidad(1L);

		srv.crearSanitario(san);


		List<Sanitario> liRes2 = srv.buscarSanitarios(null);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());
	}


	@Test
	@Transactional
	public void testModificarSanitario_UPD() throws TFCException
	{
		Sanitario san1 = srv.buscarSanitario(1L);

		Assert.assertNotNull(san1);


		san1.setColegiado("X");
		srv.modificarSanitario(san1);


		Sanitario san2 = srv.buscarSanitario(1L);

		Assert.assertNotNull(san2);
		Assert.assertEquals("X", san2.getColegiado());
	}


	//-- Metodos de gestion de Notas de sanitario.


	@Test
	public void testBuscarNotas()
	{
		List<Nota> liRes = srv.buscarNotasSanitario(idSanitario);

		Assert.assertNotNull(liRes);
		Assert.assertFalse(liRes.isEmpty());
		Assert.assertEquals(3, liRes.size());
	}


	@Test
	public void testCrearNota() throws TFCException
	{
		List<Nota> liRes1 = srv.buscarNotasSanitario(idSanitario);

		Assert.assertNotNull(liRes1);


		NotaPK pk = new NotaPK();
		pk.setIdSanitario(idSanitario);
		pk.setFecha(new Date());

		Nota nota = new Nota();
		nota.setId(pk);
		nota.setDescripcion("Nota " + new Date().toString());


		srv.crearNota(nota);

		List<Nota> liRes2 = srv.buscarNotasSanitario(idSanitario);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());
	}


	@Test
	public void testBorrarNota() throws TFCException
	{
		List<Nota> liRes1 = srv.buscarNotasSanitario(idSanitario);

		Assert.assertNotNull(liRes1);


		NotaPK pk = new NotaPK();
		pk.setIdSanitario(idSanitario);
		pk.setFecha(new Date());

		Nota nota = new Nota();
		nota.setId(pk);
		nota.setDescripcion("Nota " + new Date().toString());


		srv.crearNota(nota);

		List<Nota> liRes2 = srv.buscarNotasSanitario(idSanitario);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());

		Nota nota2 = new Nota();
		nota2.setId(pk);
		srv.borrarNota(nota2);

		List<Nota> liRes3 = srv.buscarNotasSanitario(idSanitario);

		Assert.assertNotNull(liRes3);
		Assert.assertFalse(liRes3.isEmpty());
		Assert.assertEquals(liRes1.size(), liRes3.size());
	}


}
