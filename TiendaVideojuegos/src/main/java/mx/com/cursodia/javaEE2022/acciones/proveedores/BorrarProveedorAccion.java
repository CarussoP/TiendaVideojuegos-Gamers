package mx.com.cursodia.javaEE2022.acciones.proveedores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class BorrarProveedorAccion extends AccionProveedores
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("PROV"));
			Proveedor.borrar(id);
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarProveedores.pro";
	}
}
