package pruebas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.JpaHelper;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;

/**
 * Pruebas manuales (no JUnit) contra el esquema de 5 tablas vía JPA.
 */
public class HibernatePruebas
{
	public static void main(String[] args)
	{
		HibernatePruebas hp = new HibernatePruebas();
		hp.mostrarOfertas();
		hp.probarReglaDeDecision();
	}

	private void mostrarOfertas()
	{
		EntityManagerFactory emf = JpaHelper.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		try
		{
			List<Videojuego> lista = em.createQuery("SELECT v FROM Videojuego v", Videojuego.class)
					.getResultList();
			for(Videojuego v : lista)
			{
				System.out.println(v);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			em.close();
		}
	}

	/** Ejemplo: proveedores que pueden surtir 10 unidades del titulo cat_vid_id = 1 */
	private void probarReglaDeDecision()
	{
		try
		{
			List<Videojuego> proveedores = DaoFactory.getVideojuegoConsultaDAO()
					.buscarProveedoresQuePuedenSurtir(1, 10);
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
