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

import rob.proyecto.tfc.data.entity.Paciente;


/**
 * Test de la implementacion de servicio "PacientesDAO".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class PacientesDAOImplTest
{

	@Autowired
	@Qualifier("PacientesDAO")
	private PacientesDAOImpl dao;

	private Paciente entidad = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		entidad = new Paciente();
		entidad.setId(1L);
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
		Paciente resultado = dao.buscarPK(entidad, entidad.getId());

		Assert.assertNotNull(resultado);
		Assert.assertEquals(entidad.getId(), resultado.getId());
	}


	@Test
	public final void testBuscarPacientes()
	{
		List<?> liResultado = null;

		entidad = new Paciente();
		entidad.setExpediente("E0001");

		liResultado = dao.buscarByExample(entidad);

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
		Assert.assertTrue(liResultado.size() == 1);


		entidad = new Paciente();
		entidad.setDni("000001");

		liResultado = dao.buscarByExample(entidad);

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
		Assert.assertTrue(liResultado.size() == 1);


		entidad = null;

		liResultado = dao.buscarByExample(entidad);

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
		Assert.assertTrue(liResultado.size() == 5);
	}


	@Test
	public final void testBuscarPacientesHospitalizados()
	{
		List<?> liResultado = null;

		entidad = new Paciente();
		entidad.setHospitalizado(Boolean.TRUE);

		liResultado = dao.buscarByExample(entidad);

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
		Assert.assertTrue(liResultado.size() == 2);


		entidad = new Paciente();
		entidad.setHospitalizado(Boolean.FALSE);

		liResultado = dao.buscarByExample(entidad);

		Assert.assertNotNull(liResultado);
		Assert.assertFalse(liResultado.isEmpty());
		Assert.assertTrue(liResultado.size() == 3);
	}

}
