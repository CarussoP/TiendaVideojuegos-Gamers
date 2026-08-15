package mx.com.cursodia.javaEE2022.acciones;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contrato de todas las acciones del sistema (Command). Ademas trae el
 * mecanismo de resolucion por reflection que soporta el OCP: para agregar
 * una accion nueva basta con crear una clase "{Tipo}Accion" en el paquete
 * correspondiente, sin tocar ningun controlador ni esta clase.
 *
 * A diferencia de la version original (un solo dominio), este resolver ya
 * NO asume un unico paquete de acciones: recibe el paquete destino como
 * parametro. Cada dominio (videojuegos, proveedores, plataformas,
 * asignaciones) expone su propia fachada de una linea sobre este metodo
 * (ver AccionVideojuegos, AccionProveedores, etc.) para no repetir el
 * try/catch de reflection en cada controlador (DRY).
 */
public abstract class Accion
{
	public abstract String ejecutar(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Resuelve e instancia la Accion concreta para una URL tipo "/Tipo.ext"
	 * dentro del paquete indicado.
	 *
	 * @param urlServlet   el path del servlet, p.ej. "/MostrarVideojuegos.do"
	 * @param extension    la extension a recortar, p.ej. "do", "pro", "pla", "asig"
	 * @param paqueteAcciones el paquete donde viven las clases "{Tipo}Accion" de ese dominio
	 */
	protected static Accion resolver(String urlServlet, String extension, String paqueteAcciones) throws AccionException
	{
		String tipo = urlServlet.substring(1, urlServlet.length() - (extension.length() + 1));
		String fqcn = paqueteAcciones + "." + tipo + "Accion";
		try
		{
			Class<?> c = Class.forName(fqcn);
			return (Accion) c.getDeclaredConstructor().newInstance();
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | ClassCastException e)
		{
			throw new AccionException("No existe una accion registrada para: " + fqcn, e);
		}
	}
}
