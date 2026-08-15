package mx.com.cursodia.javaEE2022.acciones.plataformas;

import mx.com.cursodia.javaEE2022.acciones.Accion;
import mx.com.cursodia.javaEE2022.acciones.AccionException;

/**
 * Punto de entrada de reflection para el dominio Plataformas (cat_plataformas).
 */
public abstract class AccionPlataformas extends Accion
{
	private static final String PAQUETE = "mx.com.cursodia.javaEE2022.acciones.plataformas";
	private static final String EXTENSION = "pla";

	public static Accion getAccion(String urlServlet) throws AccionException
	{
		return resolver(urlServlet, EXTENSION, PAQUETE);
	}
}
