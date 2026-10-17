package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoConsultaDAO;

/**
 * Reemplaza el "if" hardcodeado que antes vivia en el Controlador
 * (comparaba el parametro Proveedor contra "Seleccionar" ahi mismo). Esa
 * decision es logica de negocio de ESTE dominio, asi que ahora vive aqui:
 * el controlador vuelve a ser agnostico, solo enruta por reflection.
 */
public class FiltrarVideojuegosPorProveedorAccion extends AccionVideojuegos
{
	private final IVideojuegoConsultaDAO videojuegoConsultaDAO = DaoFactory.getVideojuegoConsultaDAO();
	private final IProveedorConsultaDAO proveedorConsultaDAO = DaoFactory.getProveedorConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		String proveedorParam = request.getParameter("Proveedor");
		List<Videojuego> listaDeOfertas = null;
		List<Proveedor> listaDeProveedores = null;
		try
		{
			if(proveedorParam == null || proveedorParam.equals("Seleccionar"))
			{
				listaDeOfertas = videojuegoConsultaDAO.buscarTodos();
			}
			else
			{
				listaDeOfertas = videojuegoConsultaDAO.filtrarPorProveedor(Integer.parseInt(proveedorParam));
			}
			listaDeProveedores = proveedorConsultaDAO.buscarTodos();
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		request.setAttribute("listaDeOfertas", listaDeOfertas);
		request.setAttribute("listaDeProveedores", listaDeProveedores);
		return "/videojuegos/MostrarVideojuegos.jsp";
	}
}
