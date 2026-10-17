package mx.com.cursodia.javaEE2022.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.JpaHelper;
import mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaDAO;

/** Implementación JPA (EntityManager + JPQL) de {@link IPlataformaDAO}. */
public class PlataformaDAOJpa implements IPlataformaDAO
{
	@Override
	public List<Plataforma> buscarTodas() throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			return em.createQuery("SELECT p FROM Plataforma p ORDER BY p.cat_pla_nombre", Plataforma.class)
					.getResultList();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar plataformas", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public Plataforma buscarPorId(int id) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			return em.find(Plataforma.class, id);
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al buscar la plataforma " + id, e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void insertar(Plataforma plataforma) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.persist(plataforma);
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al insertar la plataforma", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void actualizar(Plataforma plataforma) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.merge(plataforma);
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al actualizar la plataforma " + plataforma.getCat_pla_id(), e);
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
			Plataforma p = em.find(Plataforma.class, id);
			if(p == null)
			{
				tx.rollback();
				return false;
			}
			em.remove(p);
			tx.commit();
			return true;
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al borrar la plataforma " + id, e);
		}
		finally
		{
			em.close();
		}
	}
}
