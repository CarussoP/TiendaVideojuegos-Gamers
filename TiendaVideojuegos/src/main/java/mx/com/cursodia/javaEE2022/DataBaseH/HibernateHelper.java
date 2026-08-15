package mx.com.cursodia.javaEE2022.DataBaseH;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper 
{
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory()
	{
		try 
		{
			return new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Error al crear el factory de sesion "+ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
