package mx.com.cursodia.javaEE2022.acciones.plataformas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class InsertarPlataformaAccion extends AccionPlataformas
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			Plataforma.insertar(new Plataforma(request.getParameter("NOM"), request.getParameter("COMP")));
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		return "/MostrarPlataformas.pla";
	}
}
