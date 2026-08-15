package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;

/**
 * Relacion N:M "que plataformas maneja cada proveedor".
 * Mapea la tabla proveedores_plataformas. Es la pieza clave de la regla de
 * decision: antes de asignarle una oferta a un proveedor, se valida que
 * exista un renglon aqui para su plataforma.
 */
public class ProveedorPlataforma
{
	private int pp_id;
	private Proveedor proveedor;
	private Plataforma plataforma;

	public ProveedorPlataforma() {}

	public ProveedorPlataforma(Proveedor proveedor, Plataforma plataforma)
	{
		this.proveedor = proveedor;
		this.plataforma = plataforma;
	}

	public int getPp_id() { return pp_id; }
	public void setPp_id(int pp_id) { this.pp_id = pp_id; }

	public Proveedor getProveedor() { return proveedor; }
	public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

	public Plataforma getPlataforma() { return plataforma; }
	public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

	@SuppressWarnings("unchecked")
	public static List<ProveedorPlataforma> buscarTodas() throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.createQuery("FROM ProveedorPlataforma").list();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar asignaciones proveedor-plataforma", e);
		}
		finally
		{
			session.close();
		}
	}

	public static void insertar(ProveedorPlataforma asignacion) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.persist(asignacion);
			transaction.commit();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al insertar la asignacion proveedor-plataforma", e);
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
			ProveedorPlataforma pp = session.get(ProveedorPlataforma.class, id);
			if(pp == null) { transaction.rollback(); return false; }
			session.remove(pp);
			transaction.commit();
			return true;
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al borrar la asignacion " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public String toString()
	{
		return "pp_id: " + pp_id
				+ ", proveedor: " + (proveedor != null ? proveedor.getCat_prov_nombre() : "N/A")
				+ ", plataforma: " + (plataforma != null ? plataforma.getCat_pla_nombre() : "N/A");
	}
}
