package mx.com.cursodia.javaEE2022.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.DataBaseH.JpaHelper;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorDAO;

/** Implementación JPA (EntityManager + JPQL) de {@link IProveedorDAO}. */
public class ProveedorDAOJpa implements IProveedorDAO
{
	@Override
	public List<Proveedor> buscarTodos() throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			return em.createQuery("SELECT p FROM Proveedor p ORDER BY p.cat_prov_nombre", Proveedor.class)
					.getResultList();
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al listar proveedores", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public Proveedor buscarPorId(int id) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		try
		{
			return em.find(Proveedor.class, id);
		}
		catch(Exception e)
		{
			throw new DataBaseException("Error al buscar el proveedor " + id, e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void insertar(Proveedor proveedor) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.persist(proveedor);
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al insertar el proveedor", e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void actualizar(Proveedor proveedor) throws DataBaseException
	{
		EntityManager em = JpaHelper.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.merge(proveedor);
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx.isActive()) tx.rollback();
			throw new DataBaseException("Error al actualizar el proveedor " + proveedor.getCat_prov_id(), e);
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
			Proveedor p = em.find(Proveedor.class, id);
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
			throw new DataBaseException("Error al borrar el proveedor " + id, e);
		}
		finally
		{
			em.close();
		}
	}
}
