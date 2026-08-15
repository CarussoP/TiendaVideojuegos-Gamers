package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import mx.com.cursodia.javaEE2022.acciones.Accion;
import mx.com.cursodia.javaEE2022.acciones.AccionException;

/**
 * Punto de entrada de reflection para el dominio Videojuegos (la oferta
 * proveedor-titulo). Fija su propio paquete y extension de URL; el
 * ControladorVideojuegos solo conoce este metodo, nunca las clases concretas.
 */
public abstract class AccionVideojuegos extends Accion
{
	private static final String PAQUETE = "mx.com.cursodia.javaEE2022.acciones.videojuegos";
	private static final String EXTENSION = "do";

	public static Accion getAccion(String urlServlet) throws AccionException
	{
		return resolver(urlServlet, EXTENSION, PAQUETE);
	}
}
