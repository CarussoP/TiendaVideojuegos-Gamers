package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class FormularioInsertarVideojuegoAccion extends AccionVideojuegos
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		List<VideojuegoCatalogo> listaDeCatalogo = null;
		List<Proveedor> listaDeProveedores = null;
		try
		{
			listaDeCatalogo = VideojuegoCatalogo.buscarTodos();
			listaDeProveedores = Proveedor.buscarTodos();
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
