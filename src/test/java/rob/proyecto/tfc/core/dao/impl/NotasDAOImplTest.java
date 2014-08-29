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

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.data.entity.Nota;
import rob.proyecto.tfc.data.entity.NotaPK;


/**
 * Test de la implementacion de servicio "NotasDAO".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class NotasDAOImplTest
{

	@Autowired
	@Qualifier("NotasDAO")
	private NotasDAOImpl dao;

	private Nota entidad = null;

	private final Long idSanitario = 1L;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		NotaPK pk = new NotaPK();
		pk.setIdSanitario(idSanitario);

		entidad = new Nota();
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
	public final void testBuscarPK() throws Exception
	{
		NotaPK pk = new NotaPK();
		pk.setIdSanitario(idSanitario);
		pk.setFecha(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-01-01 01:00:00"));

		entidad.setId(pk);

		Nota resultado = dao.buscarPK(entidad, entidad.getId());

		Assert.assertNotNull(resultado);
		Assert.assertEquals(entidad.getId(), resultado.getId());
	}


	@Test
	public void testBuscarNotas()
	{
		NotaPK pk = new NotaPK();
		pk.setIdSanitario(2L);

		Nota nota = new Nota();
		nota.setId(pk);

		List<Nota> liRes = dao.buscarByExample(nota);

		Assert.assertNotNull(liRes);
		Assert.assertFalse(liRes.isEmpty());
		Assert.assertEquals(2, liRes.size());
	}


	@Test
	@Transactional
	public void testCrearNota() throws TFCException
	{
		List<Nota> liRes1 = dao.buscarByExample(entidad);

		Assert.assertNotNull(liRes1);


		NotaPK pk = new NotaPK();
		pk.setIdSanitario(idSanitario);
		pk.setFecha(new Date());

		Nota nota = new Nota();
		nota.setId(pk);
		nota.setDescripcion("Nota " + new Date().toString());


		dao.crear(nota);

		List<Nota> liRes2 = dao.buscarByExample(entidad);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());
	}


	@Test
	@Transactional
	public void testBorrarNota() throws TFCException
	{
		List<Nota> liRes1 = dao.buscarByExample(entidad);

		Assert.assertNotNull(liRes1);


		NotaPK pk = new NotaPK();
		pk.setIdSanitario(idSanitario);
		pk.setFecha(new Date());

		Nota nota = new Nota();
		nota.setId(pk);
		nota.setDescripcion("Nota " + new Date().toString());


		dao.crear(nota);

		List<Nota> liRes2 = dao.buscarByExample(entidad);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());


		dao.borrar(nota);

		List<Nota> liRes3 = dao.buscarByExample(entidad);

		Assert.assertNotNull(liRes3);
		Assert.assertFalse(liRes3.isEmpty());
		Assert.assertEquals(liRes1.size(), liRes3.size());
	}
}
