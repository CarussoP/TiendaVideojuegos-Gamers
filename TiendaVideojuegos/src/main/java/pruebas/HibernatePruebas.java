package pruebas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;

/**
 * Clase de pruebas manuales (no JUnit) contra el esquema de 5 tablas.
 * Sirve para validar el mapeo Hibernate desde consola, sin pasar por
 * servlets/JSP.
 */
public class HibernatePruebas
{
	Session session = null;
	Transaction transaction = null;

	public static void main(String[] args)
	{
		HibernatePruebas hp = new HibernatePruebas();
		hp.mostrarOfertas();
		hp.probarReglaDeDecision();
	}

	private void mostrarOfertas()
	{
		try
		{
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			List<Videojuego> lista = session.createQuery("from Videojuego", Videojuego.class).list();
			for(Videojuego v : lista)
			{
				System.out.println(v);
			}
		}
		catch(HibernateException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			if(session != null) session.close();
		}
	}

	/** Ejemplo: que proveedores pueden surtir 10 unidades del titulo con cat_vid_id = 1 */
	private void probarReglaDeDecision()
	{
		try
		{
			List<Videojuego> proveedores = Videojuego.buscarProveedoresQuePuedenSurtir(1, 10);
			for(Videojuego v : proveedores)
			{
				System.out.println(v);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
