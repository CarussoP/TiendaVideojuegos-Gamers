package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;

/**
 * Catalogo de proveedores. Mapea la tabla cat_proveedores.
 * (Antes mapeaba la tabla plana "proveedores" via JDBC; migrado a Hibernate
 * al escalar al esquema de 5 tablas.)
 */
public class Proveedor
{
	private int cat_prov_id;
	private String cat_prov_nombre;
	private String cat_prov_tel;
	private String cat_prov_correo;
	private String cat_prov_direccion;

	public Proveedor() {}

	public Proveedor(String cat_prov_nombre, String cat_prov_tel, String cat_prov_correo, String cat_prov_direccion)
	{
		this.cat_prov_nombre = cat_prov_nombre;
		this.cat_prov_tel = cat_prov_tel;
		this.cat_prov_correo = cat_prov_correo;
		this.cat_prov_direccion = cat_prov_direccion;
	}

	public int getCat_prov_id() { return cat_prov_id; }
	public void setCat_prov_id(int cat_prov_id) { this.cat_prov_id = cat_prov_id; }

	public String getCat_prov_nombre() { return cat_prov_nombre; }
	public void setCat_prov_nombre(String cat_prov_nombre) { this.cat_prov_nombre = cat_prov_nombre; }

	public String getCat_prov_tel() { return cat_prov_tel; }
	public void setCat_prov_tel(String cat_prov_tel) { this.cat_prov_tel = cat_prov_tel; }

	public String getCat_prov_correo() { return cat_prov_correo; }
	public void setCat_prov_correo(String cat_prov_correo) { this.cat_prov_correo = cat_prov_correo; }

	public String getCat_prov_direccion() { return cat_prov_direccion; }
	public void setCat_prov_direccion(String cat_prov_direccion) { this.cat_prov_direccion = cat_prov_direccion; }

	@SuppressWarnings("unchecked")
	public static List<Proveedor> buscarTodos() throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.createQuery("FROM Proveedor ORDER BY cat_prov_nombre").list();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar proveedores", e);
		}
		finally
		{
			session.close();
		}
	}

	public static Proveedor buscarPorId(int id) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.get(Proveedor.class, id);
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al buscar el proveedor " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	public static void insertar(Proveedor proveedor) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.persist(proveedor);
			transaction.commit();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al insertar el proveedor", e);
		}
		finally
		{
			session.close();
		}
	}

	public static void actualizar(Proveedor proveedor) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.merge(proveedor);
			transaction.commit();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al actualizar el proveedor " + proveedor.getCat_prov_id(), e);
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
			Proveedor p = session.get(Proveedor.class, id);
			if(p == null) { transaction.rollback(); return false; }
			session.remove(p);
			transaction.commit();
			return true;
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al borrar el proveedor " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public String toString()
	{
		return "cat_prov_id: " + cat_prov_id + ", cat_prov_nombre: " + cat_prov_nombre + ", cat_prov_tel: " + cat_prov_tel
				+ ", cat_prov_correo: " + cat_prov_correo + ", cat_prov_direccion: " + cat_prov_direccion;
	}
}
