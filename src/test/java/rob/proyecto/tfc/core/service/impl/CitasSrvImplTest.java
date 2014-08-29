package rob.proyecto.tfc.core.service.impl;


import java.text.ParseException;
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

import rob.proyecto.tfc.core.exception.TFCException;
import rob.proyecto.tfc.core.service.CitasService;
import rob.proyecto.tfc.data.entity.Cita;
import rob.proyecto.tfc.data.entity.CitaPK;
import rob.proyecto.tfc.data.vo.ConsultaHoraVO;


/**
 * Test de la implementacion de servicio "CitasService".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class CitasSrvImplTest
{

	@Autowired
	@Qualifier("CitasService")
	private CitasService srv;


	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


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
	public void testBuscarCitasEspecialidad() throws Exception
	{
		Long idEspec = 1L;
		Date fechaDesde = null;
		Date fechaHasta = null;

		List<Cita> liResTodas = null;
		List<Cita> liResDesde = null;

		liResTodas = srv.buscarCitasEspecialidad(idEspec, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResTodas);
		Assert.assertFalse(liResTodas.isEmpty());

		Assert.assertEquals(4, liResTodas.size());

		//--
		fechaDesde = sdf.parse("1900-01-01 00:00:00");
		liResDesde = srv.buscarCitasEspecialidad(idEspec, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResDesde);
		Assert.assertFalse(liResDesde.isEmpty());

		Assert.assertEquals(4, liResDesde.size());

		//--
		fechaDesde = sdf.parse("2014-10-01 20:00:00");
		liResDesde = srv.buscarCitasEspecialidad(idEspec, fechaDesde, fechaHasta);

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

		liResTodas = srv.buscarCitasPaciente(idPaciente, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResTodas);
		Assert.assertFalse(liResTodas.isEmpty());

		Assert.assertEquals(2, liResTodas.size());

		//--
		fechaDesde = sdf.parse("1900-01-01 00:00:00");
		liResDesde = srv.buscarCitasPaciente(idPaciente, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResDesde);
		Assert.assertFalse(liResDesde.isEmpty());

		Assert.assertEquals(2, liResDesde.size());

		//--
		idPaciente = 2L;
		fechaDesde = sdf.parse("2014-11-01 00:00:00");
		liResDesde = srv.buscarCitasPaciente(idPaciente, fechaDesde, fechaHasta);

		Assert.assertNotNull(liResDesde);
		Assert.assertFalse(liResDesde.isEmpty());

		Assert.assertEquals(1, liResDesde.size());
	}


	@Test
	public void testBuscarHorasConsultaEspecialidad() throws ParseException
	{
		Long idEspec = 1L;
		Date fecha = sdf.parse("2014-10-01 20:00:00");

		List<ConsultaHoraVO> liRes = srv.buscarHorasConsultaEspecialidad(idEspec, fecha);

		Assert.assertNotNull(liRes);
		Assert.assertFalse(liRes.isEmpty());
	}




	@Test
	public void testCrearCita() throws TFCException
	{
		Long idPaciente = 1L;
		List<Cita> liRes1 = srv.buscarCitasPaciente(idPaciente, null, null);

		Assert.assertNotNull(liRes1);


		CitaPK pk = new CitaPK();
		pk.setIdEspecialidad(1L);
		pk.setIdPaciente(idPaciente);
		pk.setFecha(new Date());

		Cita cita = new Cita();
		cita.setId(pk);

		srv.crearCita(cita);


		List<Cita> liRes2 = srv.buscarCitasPaciente(idPaciente, null, null);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());
	}


	@Test
	public void testBorrarCita() throws TFCException
	{
		Long idPaciente = 1L;
		List<Cita> liRes1 = srv.buscarCitasPaciente(idPaciente, null, null);

		Assert.assertNotNull(liRes1);


		CitaPK pk = new CitaPK();
		pk.setIdEspecialidad(1L);
		pk.setIdPaciente(idPaciente);
		pk.setFecha(new Date());

		Cita cita = new Cita();
		cita.setId(pk);

		srv.crearCita(cita);


		List<Cita> liRes2 = srv.buscarCitasPaciente(idPaciente, null, null);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());


		Cita cita2 = new Cita();
		cita2.setId(pk);

		srv.borrarCita(cita2);

		List<Cita> liRes3 = srv.buscarCitasPaciente(idPaciente, null, null);

		Assert.assertNotNull(liRes3);
		Assert.assertFalse(liRes3.isEmpty());
		Assert.assertEquals(liRes1.size(), liRes3.size());
	}


}
