package rob.proyecto.tfc.web.listener;


import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rob.proyecto.tfc.TFCConstantes;


@WebListener
public class AppContextConfigListener implements ServletContextListener
{

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(AppContextConfigListener.class);



	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(final ServletContextEvent event)
	{
		LOG.info("Inicializando contexto: " + event);

		ServletContext ctx = event.getServletContext();


		// Cargar los valores de las constantes.

		/**
		 * Clave definida en el archivo web.xml para poder configurar la ruta
		 * donde esta el repositorio de archivos adjuntos de las pruebas.
		 */
		String rutaRepositorio = ctx.getInitParameter("TFC_RUTA_ARCHIVOS_PRUEBAS");

		// Validar la cadena con la ruta del repositorio de archivos de pruebas
		// y asegurar que termina con el carcater '/' 
		if(rutaRepositorio != null)
		{
			rutaRepositorio = rutaRepositorio.trim();

			if(!rutaRepositorio.endsWith(File.separator))
			{
				rutaRepositorio += File.separator;
			}

			TFCConstantes.TFC_RUTA_ARCHIVOS_PRUEBAS = rutaRepositorio;
		}
	}


	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(final ServletContextEvent event)
	{
		LOG.info("Destruyendo contexto: " + event);
	}

}
