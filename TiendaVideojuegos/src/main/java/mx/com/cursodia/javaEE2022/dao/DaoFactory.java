package mx.com.cursodia.javaEE2022.dao;

import mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaComandoDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorComandoDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorPlataformaComandoDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorPlataformaConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorPlataformaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoComandoDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoComandoDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoDAO;
import mx.com.cursodia.javaEE2022.dao.jpa.PlataformaDAOJpa;
import mx.com.cursodia.javaEE2022.dao.jpa.ProveedorDAOJpa;
import mx.com.cursodia.javaEE2022.dao.jpa.ProveedorPlataformaDAOJpa;
import mx.com.cursodia.javaEE2022.dao.jpa.VideojuegoCatalogoDAOJpa;
import mx.com.cursodia.javaEE2022.dao.jpa.VideojuegoDAOJpa;

/**
 * Punto único de acceso a los DAOs. Expone interfaces segregadas (ISP).
 * Las implementaciones concretas usan JPA ({@code dao.jpa}).
 */
public final class DaoFactory
{
	private static final IProveedorDAO PROVEEDOR_DAO = new ProveedorDAOJpa();
	private static final IPlataformaDAO PLATAFORMA_DAO = new PlataformaDAOJpa();
	private static final IVideojuegoCatalogoDAO VIDEOJUEGO_CATALOGO_DAO = new VideojuegoCatalogoDAOJpa();
	private static final IVideojuegoDAO VIDEOJUEGO_DAO = new VideojuegoDAOJpa();
	private static final IProveedorPlataformaDAO PROVEEDOR_PLATAFORMA_DAO = new ProveedorPlataformaDAOJpa();

	private DaoFactory() {}

	public static IProveedorConsultaDAO getProveedorConsultaDAO()
	{
		return PROVEEDOR_DAO;
	}

	public static IProveedorComandoDAO getProveedorComandoDAO()
	{
		return PROVEEDOR_DAO;
	}

	public static IProveedorDAO getProveedorDAO()
	{
		return PROVEEDOR_DAO;
	}

	public static IPlataformaConsultaDAO getPlataformaConsultaDAO()
	{
		return PLATAFORMA_DAO;
	}

	public static IPlataformaComandoDAO getPlataformaComandoDAO()
	{
		return PLATAFORMA_DAO;
	}

	public static IPlataformaDAO getPlataformaDAO()
	{
		return PLATAFORMA_DAO;
	}

	public static IVideojuegoCatalogoConsultaDAO getVideojuegoCatalogoConsultaDAO()
	{
		return VIDEOJUEGO_CATALOGO_DAO;
	}

	public static IVideojuegoCatalogoComandoDAO getVideojuegoCatalogoComandoDAO()
	{
		return VIDEOJUEGO_CATALOGO_DAO;
	}

	public static IVideojuegoCatalogoDAO getVideojuegoCatalogoDAO()
	{
		return VIDEOJUEGO_CATALOGO_DAO;
	}

	public static IVideojuegoConsultaDAO getVideojuegoConsultaDAO()
	{
		return VIDEOJUEGO_DAO;
	}

	public static IVideojuegoComandoDAO getVideojuegoComandoDAO()
	{
		return VIDEOJUEGO_DAO;
	}

	public static IVideojuegoDAO getVideojuegoDAO()
	{
		return VIDEOJUEGO_DAO;
	}

	public static IProveedorPlataformaConsultaDAO getProveedorPlataformaConsultaDAO()
	{
		return PROVEEDOR_PLATAFORMA_DAO;
	}

	public static IProveedorPlataformaComandoDAO getProveedorPlataformaComandoDAO()
	{
		return PROVEEDOR_PLATAFORMA_DAO;
	}

	public static IProveedorPlataformaDAO getProveedorPlataformaDAO()
	{
		return PROVEEDOR_PLATAFORMA_DAO;
	}
}
