package mx.com.cursodia.javaEE2022.acciones.asignaciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class FormularioInsertarAsignacionAccion extends AccionAsignaciones
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			request.setAttribute("listaDeProveedores", Proveedor.buscarTodos());
			request.setAttribute("listaDePlataformas", Plataforma.buscarTodas());
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		return "/asignaciones/FormularioAsignacion.jsp";
	}
}
