package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class BorrarVideojuegoAccion extends AccionVideojuegos
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("VJ"));
			Videojuego.borrar(id);
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarVideojuegos.do";
	}
}
