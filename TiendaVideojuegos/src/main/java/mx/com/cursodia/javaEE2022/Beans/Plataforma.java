package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;

/**
 * Catalogo de plataformas (Nintendo Switch, PS5, Xbox, etc.)
 * Mapea la tabla cat_plataformas.
 */
public class Plataforma
{
	private int cat_pla_id;
	private String cat_pla_nombre;
	private String cat_pla_compania;

	public Plataforma() {}

	public Plataforma(String cat_pla_nombre, String cat_pla_compania)
	{
		this.cat_pla_nombre = cat_pla_nombre;
		this.cat_pla_compania = cat_pla_compania;
	}

	public int getCat_pla_id() { return cat_pla_id; }
	public void setCat_pla_id(int cat_pla_id) { this.cat_pla_id = cat_pla_id; }

	public String getCat_pla_nombre() { return cat_pla_nombre; }
	public void setCat_pla_nombre(String cat_pla_nombre) { this.cat_pla_nombre = cat_pla_nombre; }

	public String getCat_pla_compania() { return cat_pla_compania; }
	public void setCat_pla_compania(String cat_pla_compania) { this.cat_pla_compania = cat_pla_compania; }

	@SuppressWarnings("unchecked")
	public static List<Plataforma> buscarTodas() throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.createQuery("FROM Plataforma ORDER BY cat_pla_nombre").list();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar plataformas", e);
		}
		finally
		{
			session.close();
		}
	}

	public static Plataforma buscarPorId(int id) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.get(Plataforma.class, id);
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al buscar la plataforma " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	public static void insertar(Plataforma plataforma) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.persist(plataforma);
			transaction.commit();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al insertar la plataforma", e);
		}
		finally
		{
			session.close();
		}
	}

	public static void actualizar(Plataforma plataforma) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.merge(plataforma);
			transaction.commit();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al actualizar la plataforma " + plataforma.getCat_pla_id(), e);
		}
		finally
		{
			session.close();
		}
	}

	public static boolean borrar(int id) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			Plataforma p = session.get(Plataforma.class, id);
			if(p == null) { transaction.rollback(); return false; }
			session.remove(p);
			transaction.commit();
			return true;
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al borrar la plataforma " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public String toString()
	{
		return "cat_pla_id: " + cat_pla_id + ", cat_pla_nombre: " + cat_pla_nombre + ", cat_pla_compania: " + cat_pla_compania;
	}
}
