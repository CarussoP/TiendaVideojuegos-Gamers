package pruebas;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Log4Prueba 
{
	public static void main(String[] args) 
	{
		Logger logger = (Logger) LogManager.getRootLogger();
    	logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile"));
	}
}
