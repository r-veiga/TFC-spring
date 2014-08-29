package rob.proyecto.tfc.web.jsf.mbean;


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

import rob.proyecto.tfc.core.service.CitasService;
import rob.proyecto.tfc.data.entity.Cita;
import rob.proyecto.tfc.data.entity.CitaPK;
import rob.proyecto.tfc.data.vo.ConsultaHoraVO;


/**
 * Test del MBean "CitasMBean".
 * 
 * @author user
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-TEST.xml" })
public class CitasMBeanTest
{

	@Autowired
	@Qualifier("CitasService")
	private CitasService citasSrv;


	private final SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private CitasMBean bean = new CitasMBean();

	private Cita cita;


	@Before
	public void setUp() throws Exception
	{
		bean = new CitasMBean();
		bean.setCitasSrv(citasSrv);


		CitaPK pk = new CitaPK();
		pk.setIdEspecialidad(1L);
		pk.setIdPaciente(1L);

		cita = new Cita();
		cita.setId(pk);


		bean.setCita(cita);
	}


	@Test
	public void testDoBuscarCitasEspecialidadProximas()
	{
		bean.doBuscarCitasEspecialidadProximas();
		List<Cita> citas = bean.getCitas();

		Assert.assertNotNull(citas);
	}


	@Test
	public void testDoBuscarCitasEspecialidadDia() throws Exception
	{
		Date fecha = sdfFecha.parse("2014-10-01 00:00:00");

		cita.getId().setIdEspecialidad(1L);
		cita.getId().setFecha(fecha);


		bean.doBuscarCitasEspecialidadDia();


		List<ConsultaHoraVO> consultas = bean.getConsultas();

		Assert.assertNotNull(consultas);
		Assert.assertEquals(2 * (20 - 8), consultas.size() - 1);

		for(ConsultaHoraVO consulta : consultas)
		{
			String shora = consulta.getHora();
			String sfecha = "///";

			if(consulta.getCita().getId() != null)
			{
				sfecha = cita.getId().getFecha().toString();
			}


			System.out.println("" + shora + " | " + sfecha);
		}
	}

	@Test
	public void testDoReservarCita()
	{
		// bean.doReservarCita();
	}

	@Test
	public void testDoAnularCita()
	{
		// bean.doAnularCita();
	}

}
