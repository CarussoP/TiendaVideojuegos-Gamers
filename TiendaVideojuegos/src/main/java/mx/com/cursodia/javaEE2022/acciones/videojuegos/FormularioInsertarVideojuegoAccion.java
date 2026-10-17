package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoConsultaDAO;

public class FormularioInsertarVideojuegoAccion extends AccionVideojuegos
{
	private final IVideojuegoCatalogoConsultaDAO catalogoConsultaDAO = DaoFactory.getVideojuegoCatalogoConsultaDAO();
	private final IProveedorConsultaDAO proveedorConsultaDAO = DaoFactory.getProveedorConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		List<VideojuegoCatalogo> listaDeCatalogo = null;
		List<Proveedor> listaDeProveedores = null;
		try
		{
			listaDeCatalogo = catalogoConsultaDAO.buscarTodos();
			listaDeProveedores = proveedorConsultaDAO.buscarTodos();
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		request.setAttribute("listaDeCatalogo", listaDeCatalogo);
		request.setAttribute("listaDeProveedores", listaDeProveedores);
		return "/videojuegos/FormularioVideojuego.jsp";
	}
}
