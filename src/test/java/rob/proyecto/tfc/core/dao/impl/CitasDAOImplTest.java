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

import rob.proyecto.tfc.data.entity.Cita;
import rob.proyecto.tfc.data.entity.CitaPK;


/**
 * Test de la implementacion de servicio "CitasDAO".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class CitasDAOImplTest
{

	@Autowired
	@Qualifier("CitasDAO")
	private CitasDAOImpl dao;


	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Cita entidad = null;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		entidad = new Cita();
		CitaPK pk = new CitaPK();

		pk.setFecha(sdf.parse("2014-10-01 10:00:00"));
		pk.setIdPaciente(1L);
		pk.setIdEspecialidad(1L);

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
		Cita resultado = dao.buscarPK(entidad, entidad.getId());

		Assert.assertNotNull(resultado);
		Assert.assertEquals(entidad.getId(), resultado.getId());
	}



	@Test
	public void testBuscarCitasEspecialidad() throws Exception
	{
		Long idEspec = 1L;
		Date fechaDesde = null;
		Date fechaHasta = null;

		List<Cita> liResTodas = null;
		List<Cita> liResDesde = null;

		liResTodas = dao.buscarCitasEspecialidad(idEspec, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResTodas);
		Assert.assertFalse(liResTodas.isEmpty());

		Assert.assertEquals(4, liResTodas.size());

		//--
		fechaDesde = sdf.parse("1900-01-01 00:00:00");
		liResDesde = dao.buscarCitasEspecialidad(idEspec, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResDesde);
		Assert.assertFalse(liResDesde.isEmpty());

		Assert.assertEquals(4, liResDesde.size());

		//--
		fechaDesde = sdf.parse("2014-10-01 20:00:00");
		liResDesde = dao.buscarCitasEspecialidad(idEspec, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResDesde);
		Assert.assertFalse(liResDesde.isEmpty());

		Assert.assertEquals(1, liResDesde.size());
	}


	@Test
	public void testBuscarCitasPaciente() throws Exception
	{
		Long idPaciente = 1L;
		Date fechaDesde = null;
		Date fechaHasta = null;

		List<Cita> liResTodas = null;
		List<Cita> liResDesde = null;

		liResTodas = dao.buscarCitasPaciente(idPaciente, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResTodas);
		Assert.assertFalse(liResTodas.isEmpty());

		Assert.assertEquals(2, liResTodas.size());

		//--
		fechaDesde = sdf.parse("1900-01-01 00:00:00");
		liResDesde = dao.buscarCitasPaciente(idPaciente, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResDesde);
		Assert.assertFalse(liResDesde.isEmpty());

		Assert.assertEquals(2, liResDesde.size());

		//--
		idPaciente = 2L;
		fechaDesde = sdf.parse("2014-11-01 00:00:00");
		liResDesde = dao.buscarCitasPaciente(idPaciente, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResDesde);
		Assert.assertFalse(liResDesde.isEmpty());

		Assert.assertEquals(1, liResDesde.size());
	}


}
