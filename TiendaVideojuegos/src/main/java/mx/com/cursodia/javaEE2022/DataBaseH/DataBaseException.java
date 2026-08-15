package mx.com.cursodia.javaEE2022.DataBaseH;

public class DataBaseException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Exception cause;
	public DataBaseException() 
	{
		super();
	}

	public DataBaseException(String message, Throwable cause)
	{
		super(message, cause);
		this.cause = (Exception) cause;
		
	}
	public DataBaseException(String message)
	{
		super(message);
	}
	public DataBaseException(Throwable cause)
	{
		super(cause);
	}
	public Exception getException()
	{
		return this.cause;
	}
}
