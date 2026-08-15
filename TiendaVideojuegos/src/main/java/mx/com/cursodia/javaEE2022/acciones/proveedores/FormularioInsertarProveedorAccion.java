package mx.com.cursodia.javaEE2022.acciones.proveedores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormularioInsertarProveedorAccion extends AccionProveedores
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		return "/proveedores/FormularioProveedor.jsp";
	}
}
