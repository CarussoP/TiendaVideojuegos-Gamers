package mx.com.cursodia.javaEE2022.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.JpaHelper;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoDAO;

/** Implementación JPA (EntityManager + JPQL) de {@link IVideojuegoDAO}. */
public class VideojuegoDAOJpa implements IVideojuegoDAO
{
	@Override
	public List<Videojuego> buscarTodos() throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			return em.createQuery(
					"SELECT DISTINCT v FROM Videojuego v "
					+ "JOIN FETCH v.videojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "JOIN FETCH v.proveedor", Videojuego.class)
					.getResultList();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar las ofertas de videojuegos", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public Videojuego buscarPorId(int id) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			List<Videojuego> result = em.createQuery(
					"SELECT v FROM Videojuego v "
					+ "JOIN FETCH v.videojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "JOIN FETCH v.proveedor "
					+ "WHERE v.vj_id = :id", Videojuego.class)
					.setParameter("id", id)
					.getResultList();
			return result.isEmpty() ? null : result.get(0);
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al buscar la oferta " + id, e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public List<Videojuego> filtrarPorProveedor(int catProvId) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			return em.createQuery(
					"SELECT DISTINCT v FROM Videojuego v "
					+ "JOIN FETCH v.videojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "JOIN FETCH v.proveedor "
					+ "WHERE v.proveedor.cat_prov_id = :idProv", Videojuego.class)
					.setParameter("idProv", catProvId)
					.getResultList();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al filtrar por proveedor " + catProvId, e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public List<Videojuego> buscarProveedoresQuePuedenSurtir(int catVidId, int cantidadRequerida)
			throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			String jpql = "SELECT DISTINCT v FROM Videojuego v "
					+ "JOIN FETCH v.videojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "JOIN FETCH v.proveedor "
					+ "WHERE c.cat_vid_id = :catVidId "
					+ "AND v.vj_cantidad >= :cantidad "
					+ "AND EXISTS (SELECT 1 FROM ProveedorPlataforma pp "
					+ "            WHERE pp.proveedor = v.proveedor "
					+ "            AND pp.plataforma = c.plataforma) "
					+ "ORDER BY v.vj_precio_compra ASC";
			TypedQuery<Videojuego> query = em.createQuery(jpql, Videojuego.class);
			query.setParameter("catVidId", catVidId);
			query.setParameter("cantidad", cantidadRequerida);
			return query.getResultList();
		}
		catch(Exception e)
		{
			throw new DataBaseException(
					"Error al calcular que proveedores pueden surtir el titulo " + catVidId, e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public int insertar(Videojuego oferta) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.persist(oferta);
			em.flush();
			tx.commit();
			return oferta.getVj_id();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al insertar la oferta de videojuego", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void actualizar(Videojuego oferta) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.merge(oferta);
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al actualizar la oferta " + oferta.getVj_id(), e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public boolean borrar(int id) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			Videojuego v = em.find(Videojuego.class, id);
			if(v == null)
			{
				tx.rollback();
				return false;
			}
			em.remove(v);
			tx.commit();
			return true;
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al borrar la oferta " + id, e);
		}
		finally
		{
			em.close();
		}
	}
}
