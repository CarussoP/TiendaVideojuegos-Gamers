package mx.com.cursodia.javaEE2022.acciones.asignaciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.ProveedorPlataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class InsertarAsignacionAccion extends AccionAsignaciones
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			Proveedor proveedor = Proveedor.buscarPorId(Integer.parseInt(request.getParameter("PROV")));
			Plataforma plataforma = Plataforma.buscarPorId(Integer.parseInt(request.getParameter("PLA")));
			ProveedorPlataforma.insertar(new ProveedorPlataforma(proveedor, plataforma));
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarAsignaciones.asig";
	}
}
