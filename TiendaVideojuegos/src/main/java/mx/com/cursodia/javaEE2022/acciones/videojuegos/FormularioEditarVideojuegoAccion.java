package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoConsultaDAO;

public class FormularioEditarVideojuegoAccion extends AccionVideojuegos
{
	private final IVideojuegoConsultaDAO videojuegoConsultaDAO = DaoFactory.getVideojuegoConsultaDAO();
	private final IVideojuegoCatalogoConsultaDAO catalogoConsultaDAO = DaoFactory.getVideojuegoCatalogoConsultaDAO();
	private final IProveedorConsultaDAO proveedorConsultaDAO = DaoFactory.getProveedorConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("VJ"));
			request.setAttribute("oferta", videojuegoConsultaDAO.buscarPorId(id));
			request.setAttribute("listaDeCatalogo", catalogoConsultaDAO.buscarTodos());
			request.setAttribute("listaDeProveedores", proveedorConsultaDAO.buscarTodos());
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/videojuegos/FormularioVideojuego.jsp";
	}
}
