package mx.com.cursodia.javaEE2022.acciones.plataformas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class ModificarPlataformaAccion extends AccionPlataformas
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("PLA"));
			Plataforma plataforma = new Plataforma(request.getParameter("NOM"), request.getParameter("COMP"));
			plataforma.setCat_pla_id(id);
			Plataforma.actualizar(plataforma);
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarPlataformas.pla";
	}
}
