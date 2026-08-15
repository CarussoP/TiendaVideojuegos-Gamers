package mx.com.cursodia.javaEE2022.acciones.asignaciones;

import mx.com.cursodia.javaEE2022.acciones.Accion;
import mx.com.cursodia.javaEE2022.acciones.AccionException;

/**
 * Punto de entrada de reflection para el dominio Asignaciones
 * (proveedores_plataformas: que plataformas maneja cada proveedor).
 */
public abstract class AccionAsignaciones extends Accion
{
	private static final String PAQUETE = "mx.com.cursodia.javaEE2022.acciones.asignaciones";
	private static final String EXTENSION = "asig";

	public static Accion getAccion(String urlServlet) throws AccionException
	{
		return resolver(urlServlet, EXTENSION, PAQUETE);
	}
}
