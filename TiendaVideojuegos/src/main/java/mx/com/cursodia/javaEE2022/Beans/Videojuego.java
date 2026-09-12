package mx.com.cursodia.javaEE2022.Beans;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.HibernateHelper;

/**
 * La OFERTA de un proveedor sobre un titulo del catalogo: a que precio lo
 * compra la tienda, a que precio lo vende, y cuanta cantidad tiene ese
 * proveedor en particular. Mapea la tabla "videojuego" (singular).
 *
 * En el esquema de 2 tablas (gamers2.sql) "Videojuego" era el titulo mismo.
 * Al escalar a multiples proveedores, el titulo se separo en
 * {@link VideojuegoCatalogo} y esta clase paso a representar el renglon de
 * "quien lo vende y en que condiciones".
 */
public class Videojuego
{
	private int vj_id;
	private VideojuegoCatalogo videojuegoCatalogo;
	private Proveedor proveedor;
	private float vj_precio_compra;
	private float vj_precio_venta;
	private int vj_cantidad;

	public Videojuego() {}

	public Videojuego(VideojuegoCatalogo videojuegoCatalogo, Proveedor proveedor, float vj_precio_compra, float vj_precio_venta, int vj_cantidad)
	{
		this.videojuegoCatalogo = videojuegoCatalogo;
		this.proveedor = proveedor;
		this.vj_precio_compra = vj_precio_compra;
		this.vj_precio_venta = vj_precio_venta;
		this.vj_cantidad = vj_cantidad;
	}

	public int getVj_id() { return vj_id; }
	public void setVj_id(int vj_id) { this.vj_id = vj_id; }

	public VideojuegoCatalogo getVideojuegoCatalogo() { return videojuegoCatalogo; }
	public void setVideojuegoCatalogo(VideojuegoCatalogo videojuegoCatalogo) { this.videojuegoCatalogo = videojuegoCatalogo; }

	public Proveedor getProveedor() { return proveedor; }
	public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

	public float getVj_precio_compra() { return vj_precio_compra; }
	public void setVj_precio_compra(float vj_precio_compra) { this.vj_precio_compra = vj_precio_compra; }

	public float getVj_precio_venta() { return vj_precio_venta; }
	public void setVj_precio_venta(float vj_precio_venta) { this.vj_precio_venta = vj_precio_venta; }

	public int getVj_cantidad() { return vj_cantidad; }
	public void setVj_cantidad(int vj_cantidad) { this.vj_cantidad = vj_cantidad; }

	@SuppressWarnings("unchecked")
	public static List<Videojuego> buscarTodos() throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.createQuery(
					"SELECT DISTINCT v FROM Videojuego v "
					+ "JOIN FETCH v.videojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "JOIN FETCH v.proveedor").list();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar las ofertas de videojuegos", e);
		}
		finally
		{
			session.close();
		}
	}

	public static Videojuego buscarPorId(int id) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.createQuery(
					"SELECT v FROM Videojuego v "
					+ "JOIN FETCH v.videojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "JOIN FETCH v.proveedor "
					+ "WHERE v.vj_id = :id", Videojuego.class)
					.setParameter("id", id)
					.uniqueResult();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al buscar la oferta " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Videojuego> filtrarPorProveedor(int cat_prov_id) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			return session.createQuery(
					"SELECT DISTINCT v FROM Videojuego v "
					+ "JOIN FETCH v.videojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "JOIN FETCH v.proveedor "
					+ "WHERE v.proveedor.cat_prov_id = :idProv")
					.setParameter("idProv", cat_prov_id)
					.list();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al filtrar por proveedor " + cat_prov_id, e);
		}
		finally
		{
			session.close();
		}
	}

	/**
	 * REGLA DE DECISION: dado un titulo del catalogo y una cantidad requerida,
	 * regresa las ofertas de los proveedores que (a) tienen suficiente stock
	 * y (b) estan autorizados para la plataforma de ese titulo segun
	 * ProveedorPlataforma. Es el join de las 3 tablas nuevas del esquema.
	 */
	@SuppressWarnings("unchecked")
	public static List<Videojuego> buscarProveedoresQuePuedenSurtir(int cat_vid_id, int cantidadRequerida) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		try
		{
			String hql = "SELECT DISTINCT v FROM Videojuego v "
					+ "JOIN FETCH v.videojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "JOIN FETCH v.proveedor "
					+ "WHERE c.cat_vid_id = :catVidId "
					+ "AND v.vj_cantidad >= :cantidad "
					+ "AND EXISTS (FROM ProveedorPlataforma pp "
					+ "            WHERE pp.proveedor = v.proveedor "
					+ "            AND pp.plataforma = c.plataforma) "
					+ "ORDER BY v.vj_precio_compra ASC";
			Query<Videojuego> query = session.createQuery(hql, Videojuego.class);
			query.setParameter("catVidId", cat_vid_id);
			query.setParameter("cantidad", cantidadRequerida);
			return query.list();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al calcular que proveedores pueden surtir el titulo " + cat_vid_id, e);
		}
		finally
		{
			session.close();
		}
	}

	/** Inserta la oferta; el ID lo genera la BD (AUTO_INCREMENT / generator native). */
	public static int insertar(Videojuego oferta) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.persist(oferta);
			session.flush(); // fuerza el INSERT para poblar vj_id
			transaction.commit();
			return oferta.getVj_id();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al insertar la oferta de videojuego", e);
		}
		finally
		{
			session.close();
		}
	}

	public static void actualizar(Videojuego oferta) throws DataBaseException
	{
		Session session = HibernateHelper.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.merge(oferta);
			transaction.commit();
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al actualizar la oferta " + oferta.getVj_id(), e);
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
			Videojuego v = session.get(Videojuego.class, id);
			if(v == null) { transaction.rollback(); return false; }
			session.remove(v);
			transaction.commit();
			return true;
		}
		catch(Exception e)
		{
			transaction.rollback();
			throw new DataBaseException("Error al borrar la oferta " + id, e);
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public String toString()
	{
		return "vj_id: " + vj_id
				+ ", titulo: " + (videojuegoCatalogo != null ? videojuegoCatalogo.getCat_vid_nombre() : "N/A")
				+ ", proveedor: " + (proveedor != null ? proveedor.getCat_prov_nombre() : "N/A")
				+ ", vj_precio_compra: " + vj_precio_compra
				+ ", vj_precio_venta: " + vj_precio_venta
				+ ", vj_cantidad: " + vj_cantidad;
	}
}
