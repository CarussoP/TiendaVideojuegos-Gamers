package mx.com.cursodia.javaEE2022.DataBaseH;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Crea y expone el {@link EntityManagerFactory} de la unidad de persistencia
 * {@code gamersPU} definida en {@code META-INF/persistence.xml}.
 */
public final class JpaHelper
{
	public static final String PERSISTENCE_UNIT = "gamersPU";

	private static final EntityManagerFactory EMF = buildEntityManagerFactory();

	private JpaHelper() {}

	private static EntityManagerFactory buildEntityManagerFactory()
	{
		try
		{
			return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		}
		catch(Throwable ex)
		{
			System.err.println("Error al crear el EntityManagerFactory: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory()
	{
		return EMF;
	}

	/** Abre un EntityManager nuevo (el llamador debe cerrarlo). */
	public static EntityManager createEntityManager()
	{
		return EMF.createEntityManager();
	}
}
