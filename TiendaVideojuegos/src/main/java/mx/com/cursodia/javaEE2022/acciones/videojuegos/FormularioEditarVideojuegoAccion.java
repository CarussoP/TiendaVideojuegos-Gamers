package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class FormularioEditarVideojuegoAccion extends AccionVideojuegos
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("VJ"));
			request.setAttribute("oferta", Videojuego.buscarPorId(id));
			request.setAttribute("listaDeCatalogo", VideojuegoCatalogo.buscarTodos());
			request.setAttribute("listaDeProveedores", Proveedor.buscarTodos());
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/videojuegos/FormularioVideojuego.jsp";
	}
}
