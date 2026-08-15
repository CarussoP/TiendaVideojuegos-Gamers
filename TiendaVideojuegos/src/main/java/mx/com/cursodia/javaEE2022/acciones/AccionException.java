package mx.com.cursodia.javaEE2022.acciones;

/**
 * Se lanza cuando el mecanismo de reflection de {@link Accion} no logra
 * resolver o instanciar la clase de accion correspondiente a una URL.
 */
public class AccionException extends Exception
{
	private static final long serialVersionUID = 1L;

	public AccionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public AccionException(String message)
	{
		super(message);
	}
}
