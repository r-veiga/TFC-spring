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
import rob.proyecto.tfc.core.service.PacientesService;
import rob.proyecto.tfc.data.entity.Paciente;


/**
 * Test de la implementacion de servicio "PacientesService".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class PacientesSrvImplTest
{

	@Autowired
	@Qualifier("PacientesService")
	private PacientesService srv;

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
	public final void testBuscarPacientesAmbulatorio()
	{
		List<Paciente> liResTodos = srv.buscarPacientes(null);

		Assert.assertFalse(liResTodos.isEmpty());
		Assert.assertEquals(5, liResTodos.size());

		Paciente paciente = new Paciente();
		paciente.setHospitalizado(Boolean.FALSE);

		List<Paciente> liResFiltrado = srv.buscarPacientes(paciente);

		Assert.assertFalse(liResFiltrado.isEmpty());
		Assert.assertEquals(3, liResFiltrado.size());

		Assert.assertTrue(liResTodos.size() > liResFiltrado.size());
	}


	@Test
	public final void testBuscarPacientesHospitalizados()
	{
		List<Paciente> liResTodos = srv.buscarPacientes(null);

		Assert.assertFalse(liResTodos.isEmpty());


		Paciente paciente = new Paciente();
		paciente.setHospitalizado(Boolean.TRUE);

		List<Paciente> liResFiltrado = srv.buscarPacientes(paciente);

		Assert.assertFalse(liResFiltrado.isEmpty());
		Assert.assertEquals(2, liResFiltrado.size());

		Assert.assertTrue(liResTodos.size() > liResFiltrado.size());
	}


	@Test
	@Transactional
	public void testCrearPaciente() throws TFCException
	{
		List<Paciente> liRes1 = srv.buscarPacientes(null);

		Assert.assertNotNull(liRes1);


		Paciente pac = new Paciente();

		pac.setExpediente("X");
		pac.setNombre("X");
		pac.setApellidos("X");
		pac.setDni("X");

		srv.crearPaciente(pac);


		List<Paciente> liRes2 = srv.buscarPacientes(null);

		Assert.assertNotNull(liRes2);
		Assert.assertFalse(liRes2.isEmpty());
		Assert.assertEquals(liRes1.size() + 1, liRes2.size());
	}


	@Test
	@Transactional
	public void testModificarPaciente_UPD() throws TFCException
	{
		Paciente pac1 = srv.buscarPaciente(1L);

		Assert.assertNotNull(pac1);


		pac1.setExpediente("X");
		srv.modificarPaciente(pac1);


		Paciente pac2 = srv.buscarPaciente(1L);

		Assert.assertNotNull(pac2);
		Assert.assertEquals("X", pac2.getExpediente());
	}


}
