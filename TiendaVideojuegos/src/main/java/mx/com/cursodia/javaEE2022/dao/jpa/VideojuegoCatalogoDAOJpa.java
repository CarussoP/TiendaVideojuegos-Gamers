package mx.com.cursodia.javaEE2022.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.JpaHelper;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoDAO;

/** Implementación JPA (EntityManager + JPQL) de {@link IVideojuegoCatalogoDAO}. */
public class VideojuegoCatalogoDAOJpa implements IVideojuegoCatalogoDAO
{
	@Override
	public List<VideojuegoCatalogo> buscarTodos() throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			return em.createQuery(
					"SELECT DISTINCT c FROM VideojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "ORDER BY c.cat_vid_nombre", VideojuegoCatalogo.class)
					.getResultList();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar el catalogo de videojuegos", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public VideojuegoCatalogo buscarPorId(int id) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			List<VideojuegoCatalogo> result = em.createQuery(
					"SELECT c FROM VideojuegoCatalogo c "
					+ "LEFT JOIN FETCH c.plataforma "
					+ "WHERE c.cat_vid_id = :id", VideojuegoCatalogo.class)
					.setParameter("id", id)
					.getResultList();
			return result.isEmpty() ? null : result.get(0);
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al buscar el titulo " + id, e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void insertar(VideojuegoCatalogo catalogo) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.persist(catalogo);
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al insertar el titulo en el catalogo", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void actualizar(VideojuegoCatalogo catalogo) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.merge(catalogo);
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al actualizar el titulo " + catalogo.getCat_vid_id(), e);
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
			VideojuegoCatalogo v = em.find(VideojuegoCatalogo.class, id);
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
			throw new DataBaseException("Error al borrar el titulo " + id, e);
		}
		finally
		{
			em.close();
		}
	}
}
