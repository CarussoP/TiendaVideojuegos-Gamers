package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class MostrarVideojuegosAccion extends AccionVideojuegos
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		List<Videojuego> listaDeOfertas = null;
		List<Proveedor> listaDeProveedores = null;
		try
		{
			listaDeOfertas = Videojuego.buscarTodos();
			listaDeProveedores = Proveedor.buscarTodos();
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
