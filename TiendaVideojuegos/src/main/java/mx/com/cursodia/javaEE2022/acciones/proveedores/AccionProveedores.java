package mx.com.cursodia.javaEE2022.acciones.proveedores;

import mx.com.cursodia.javaEE2022.acciones.Accion;
import mx.com.cursodia.javaEE2022.acciones.AccionException;

/**
 * Punto de entrada de reflection para el dominio Proveedores (cat_proveedores).
 */
public abstract class AccionProveedores extends Accion
{
	private static final String PAQUETE = "mx.com.cursodia.javaEE2022.acciones.proveedores";
	private static final String EXTENSION = "pro";

	public static Accion getAccion(String urlServlet) throws AccionException
	{
		return resolver(urlServlet, EXTENSION, PAQUETE);
	}
}
