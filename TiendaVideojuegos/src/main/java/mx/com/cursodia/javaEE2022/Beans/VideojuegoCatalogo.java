package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;

/**
 * Catalogo de titulos (el "producto" en abstracto: nombre + plataforma).
 * Mapea la tabla cat_videojuegos. Distinto de {@link Videojuego}, que es la
 * OFERTA concreta de un proveedor sobre uno de estos titulos (precio, stock).
 */
public class VideojuegoCatalogo
{
	private int cat_vid_id;
	private String cat_vid_nombre;
	private Plataforma plataforma;
	private int cat_vid_inventario;

	public VideojuegoCatalogo() {}

	public VideojuegoCatalogo(String cat_vid_nombre, Plataforma plataforma, int cat_vid_inventario)
	{
		this.cat_vid_nombre = cat_vid_nombre;
		this.plataforma = plataforma;
		this.cat_vid_inventario = cat_vid_inventario;
	}

	public int getCat_vid_id() { return cat_vid_id; }
	public void setCat_vid_id(int cat_vid_id) { this.cat_vid_id = cat_vid_id; }

	public String getCat_vid_nombre() { return cat_vid_nombre; }
	public void setCat_vid_nombre(String cat_vid_nombre) { this.cat_vid_nombre = cat_vid_nombre; }

	public Plataforma getPlataforma() { return plataforma; }
	public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

	public int getCat_vid_inventario() { return cat_vid_inventario; }
	public void setCat_vid_inventario(int cat_vid_inventario) { this.cat_vid_inventario = cat_vid_inventario; }

	@SuppressWarnings("unchecked")
	public static List<VideojuegoCatalogo> buscarTodos() throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.createQuery(
					"SELECT DISTINCT c FROM VideojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "ORDER BY c.cat_vid_nombre").list();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar el catalogo de videojuegos", e);
		}
		finally
		{
			session.close();
		}
	}

	public static VideojuegoCatalogo buscarPorId(int id) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.createQuery(
					"SELECT c FROM VideojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "WHERE c.cat_vid_id = :id", VideojuegoCatalogo.class)
					.setParameter("id", id)
					.uniqueResult();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al buscar el titulo " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	public static void insertar(VideojuegoCatalogo catalogo) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.persist(catalogo);
			transaction.commit();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al insertar el titulo en el catalogo", e);
		}
		finally
		{
			session.close();
		}
	}

	public static void actualizar(VideojuegoCatalogo catalogo) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.merge(catalogo);
			transaction.commit();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al actualizar el titulo " + catalogo.getCat_vid_id(), e);
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
			VideojuegoCatalogo v = session.get(VideojuegoCatalogo.class, id);
			if(v == null) { transaction.rollback(); return false; }
			session.remove(v);
			transaction.commit();
			return true;
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al borrar el titulo " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public String toString()
	{
		return "cat_vid_id: " + cat_vid_id + ", cat_vid_nombre: " + cat_vid_nombre
				+ ", plataforma: " + (plataforma != null ? plataforma.getCat_pla_nombre() : "N/A")
				+ ", cat_vid_inventario: " + cat_vid_inventario;
	}
}
