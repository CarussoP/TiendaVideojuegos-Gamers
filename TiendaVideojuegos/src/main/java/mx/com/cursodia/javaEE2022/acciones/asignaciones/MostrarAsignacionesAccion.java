package mx.com.cursodia.javaEE2022.acciones.asignaciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.ProveedorPlataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class MostrarAsignacionesAccion extends AccionAsignaciones
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		List<ProveedorPlataforma> listaDeAsignaciones = null;
		try
		{
			listaDeAsignaciones = ProveedorPlataforma.buscarTodas();
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		request.setAttribute("listaDeAsignaciones", listaDeAsignaciones);
		return "/asignaciones/MostrarAsignaciones.jsp";
	}
}
