package rob.proyecto.tfc.web.jsf.mbean;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rob.proyecto.tfc.TFCConstantes;
import rob.proyecto.tfc.core.service.PruebasService;
import rob.proyecto.tfc.data.entity.Prueba;
import rob.proyecto.tfc.data.entity.PruebaPK;


/**
 * ManageBean para la gestion de Pruebas.
 * 
 * @author user
 * 
 */
@ManagedBean(name = "PruebasMBean")
@ViewScoped
public class PruebasMBean extends AbstractMBean implements Serializable
{

	/** ID Serializacion. */
	private static final long serialVersionUID = 1L;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(PruebasMBean.class);


	/** Servicio spring de gestion de pruebas. */
	@ManagedProperty(value = "#{PruebasService}")
	private PruebasService pruebasSrv;


	/** Entidad para el manejo de la lista. */
	private Prueba prueba = new Prueba();

	/** Lista de entidades. */
	private List<Prueba> pruebas;

	/** Entidad para la creacion de una nueva prueba. */
	private Prueba pruebaNew = new Prueba();

	/** Objeto para la subida del archivo adjunto a la Prueba (pruebaNEW). **/
	private UploadedFile archivoUpload;

	/** Objeto para la descarga del archivo adjunto a la Prueba. **/
	private StreamedContent archivoDownload;


	//---------- Carga del servicio Spring.


	/**
	 * @param pruebasSrv the pruebasSrv to set
	 */
	public final void setPruebasSrv(final PruebasService pruebasSrv)
	{
		this.pruebasSrv = pruebasSrv;
	}


	//---------- Inicializacion del MBean.


	/* (non-Javadoc)
	 * @see rob.proyecto.tfc.web.jsf.mbean.AbstractMBean#inicializar()
	 */
	@Override
	protected void inicializar()
	{
		cargarDatos();
	}


	//---------- Metodos de Getter y Setter de los atributos.


	/**
	 * @return the prueba
	 */
	public final Prueba getPrueba()
	{
		return prueba;
	}

	/**
	 * @param prueba the prueba to set
	 */
	public final void setPrueba(final Prueba prueba)
	{
		this.prueba = prueba;
	}


	/**
	 * @return the pruebas
	 */
	public final List<Prueba> getPruebas()
	{
		return pruebas;
	}

	/**
	 * @param pruebas the pruebas to set
	 */
	public final void setPruebas(final List<Prueba> pruebas)
	{
		this.pruebas = pruebas;
	}

	/**
	 * @return the pruebaNew
	 */
	public final Prueba getPruebaNew()
	{
		return pruebaNew;
	}

	/**
	 * @param pruebaNew the pruebaNew to set
	 */
	public final void setPruebaNew(final Prueba pruebaNew)
	{
		this.pruebaNew = pruebaNew;
	}

	/**
	 * @return the archivoUpload
	 */
	public final UploadedFile getArchivoUpload()
	{
		return archivoUpload;
	}

	/**
	 * @param archivoUpload the archivoUpload to set
	 */
	public final void setArchivoUpload(final UploadedFile archivoUpload)
	{
		this.archivoUpload = archivoUpload;
	}


	/**
	 * @return the archivoDownload
	 */
	public final StreamedContent getArchivoDownload()
	{
		if(prueba != null)
		{
			InputStream stream = null;

			String archivo = TFCConstantes.TFC_RUTA_ARCHIVOS_PRUEBAS + prueba.getArchivo();

			try
			{
				stream = new FileInputStream(archivo);

				LOG.info("Descarga del archivo: '{}'", archivo);

				archivoDownload = new DefaultStreamedContent(stream, "application/octet-stream", prueba.getArchivo());

				verMensajeInfo("download.ok");
			}
			catch(FileNotFoundException e)
			{
				String msg = "Descarga de prueba";
				msg += " Pac:'" + prueba.getId().getIdPaciente() + "'";
				msg += " Fil:'" + archivo + "'";

				verMensajeInfo("download.error");

				LOG.error(msg, e);
			}

		}

		return archivoDownload;
	}

	/**
	 * @param archivoDownload the archivoDownload to set
	 */
	public final void setArchivoDownload(final StreamedContent archivoDownload)
	{
		this.archivoDownload = archivoDownload;
	}


	//---------- Metodos de captura de eventos.


	public void onRowSelect(final SelectEvent event)
	{
		prueba = (Prueba)event.getObject();
	}


	//---------- Metodos de logica.


	/**
	 * Metodo para subir el archivo de una nueva prueba.
	 */
	public void doUploadArchivo()
	{
		if(archivoUpload == null)
		{
			verMensajeInfo("upload.error");
		}
		else
		{
			verMensajeInfo("upload.ok");
			doCrearPrueba();
		}
	}


	/**
	 * Metodo para crear una nueva prueba.
	 */
	public String doCrearPrueba()
	{
		String resultado = JSF_ACT_RESPONSE_FAILURE;

		Long idPac = getSessionMBean().getPacienteSelected().getId();
		Date fecha = new Date();

		String nombreZip = "" + idPac.toString() + "_" + fecha.getTime() + ".zip";

		crearArchivoZip(nombreZip);

		PruebaPK id = new PruebaPK();
		id.setFecAlta(fecha);
		id.setIdPaciente(getSessionMBean().getPacienteSelected().getId());

		pruebaNew.setId(id);
		pruebaNew.setArchivo(nombreZip);

		try
		{
			prueba = pruebasSrv.crearPrueba(pruebaNew);

			cargarDatos();

			verMensajeInfo("crear.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error creando prueba.", e);
			verMensajeError("crear.error");
		}

		return resultado;
	}


	/**
	 * Metodo para borrar una nota.
	 */
	public String doBorrarPrueba()
	{
		String resultado = JSF_ACT_RESPONSE_SUCCESS;

		try
		{
			borrarArchivoZip(prueba.getArchivo());

			pruebasSrv.borrarPrueba(prueba);

			cargarDatos();

			verMensajeInfo("borrar.ok");
			resultado = JSF_ACT_RESPONSE_SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Error al borrar Nota.", e);
			verMensajeError("borrar.error");
		}

		return resultado;
	}


	/**
	 * Metodo para la carga e inicializacion de los datos de la pantalla.
	 */
	private void cargarDatos()
	{
		Long idPaciente = getSessionMBean().getPacienteSelected().getId();

		LOG.info("Cargando pruebas del paciente: '{}'", idPaciente);

		pruebas = pruebasSrv.buscarPruebasdePaciente(idPaciente);

		prueba = new Prueba();

		pruebaNew = new Prueba();
		pruebaNew.setDescripcion("");
	}


	/**
	 * Metodo para crear y guardar en el repositorio de archivos
	 * el archivo adjunto de la prueba.
	 * 
	 * @param nombreZip
	 * @return
	 */
	private boolean crearArchivoZip(final String nombreZip)
	{
		boolean bOk = false;

		String zipFile = TFCConstantes.TFC_RUTA_ARCHIVOS_PRUEBAS + nombreZip;

		try
		{
			// create byte buffer
			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(zipFile);

			ZipOutputStream zos = new ZipOutputStream(fos);

			InputStream inStr = archivoUpload.getInputstream();

			// begin writing a new ZIP entry, positions the stream to the start of the entry data
			zos.putNextEntry(new ZipEntry(archivoUpload.getFileName()));


			int length;

			while((length = inStr.read(buffer)) > 0)
			{
				zos.write(buffer, 0, length);
			}

			zos.closeEntry();

			// close the InputStream
			inStr.close();

			// close the ZipOutputStream
			zos.close();

			bOk = true;
		}
		catch(IOException ioe)
		{
			System.out.println("Error creating zip file" + ioe);
		}

		return bOk;
	}


	/**
	 * Metodo para borrardel repositorio de archivos el archivo adjunto a la prueba.
	 * 
	 * @param nombreArchivo
	 * @return
	 */
	private boolean borrarArchivoZip(final String nombreArchivo)
	{
		boolean bOk = false;

		String zipFile = TFCConstantes.TFC_RUTA_ARCHIVOS_PRUEBAS + nombreArchivo;

		File archivoPrueba = new File(zipFile);

		if(archivoPrueba.exists() && archivoPrueba.canWrite())
		{
			bOk = archivoPrueba.delete();
		}

		return bOk;
	}


}
