package mx.com.cursodia.javaEE2022.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import mx.com.cursodia.javaEE2022.Beans.ProveedorPlataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.JpaHelper;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorPlataformaDAO;

/** Implementación JPA (EntityManager + JPQL) de {@link IProveedorPlataformaDAO}. */
public class ProveedorPlataformaDAOJpa implements IProveedorPlataformaDAO
{
	@Override
	public List<ProveedorPlataforma> buscarTodas() throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			return em.createQuery(
					"SELECT DISTINCT pp FROM ProveedorPlataforma pp "
					+ "JOIN FETCH pp.proveedor "
					+ "JOIN FETCH pp.plataforma", ProveedorPlataforma.class)
					.getResultList();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar asignaciones proveedor-plataforma", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void insertar(ProveedorPlataforma asignacion) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.persist(asignacion);
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al insertar la asignacion proveedor-plataforma", e);
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
			ProveedorPlataforma pp = em.find(ProveedorPlataforma.class, id);
			if(pp == null)
			{
				tx.rollback();
				return false;
			}
			em.remove(pp);
			tx.commit();
			return true;
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al borrar la asignacion " + id, e);
		}
		finally
		{
			em.close();
		}
	}
}
