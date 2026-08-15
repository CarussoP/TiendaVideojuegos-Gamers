package mx.com.cursodia.javaEE2022.acciones.plataformas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormularioInsertarPlataformaAccion extends AccionPlataformas
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		return "/plataformas/FormularioPlataforma.jsp";
	}
}
