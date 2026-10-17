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

public class MostrarVideojuegosAccion extends AccionVideojuegos
{
	private final IVideojuegoConsultaDAO videojuegoConsultaDAO = DaoFactory.getVideojuegoConsultaDAO();
	private final IProveedorConsultaDAO proveedorConsultaDAO = DaoFactory.getProveedorConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		List<Videojuego> listaDeOfertas = null;
		List<Proveedor> listaDeProveedores = null;
		try
		{
			listaDeOfertas = videojuegoConsultaDAO.buscarTodos();
			listaDeProveedores = proveedorConsultaDAO.buscarTodos();
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		request.setAttribute("listaDeOfertas", listaDeOfertas);
		request.setAttribute("listaDeProveedores", listaDeProveedores);
		return "/videojuegos/MostrarVideojuegos.jsp";
	}
}
