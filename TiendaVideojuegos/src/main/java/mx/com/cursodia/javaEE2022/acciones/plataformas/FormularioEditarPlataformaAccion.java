package mx.com.cursodia.javaEE2022.acciones.plataformas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class FormularioEditarPlataformaAccion extends AccionPlataformas
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("PLA"));
			request.setAttribute("plataforma", Plataforma.buscarPorId(id));
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/plataformas/FormularioPlataforma.jsp";
	}
}
