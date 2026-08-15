package mx.com.cursodia.javaEE2022.acciones.proveedores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class MostrarProveedoresAccion extends AccionProveedores
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		List<Proveedor> listaDeProveedores = null;
		try
		{
			listaDeProveedores = Proveedor.buscarTodos();
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		request.setAttribute("listaDeProveedores", listaDeProveedores);
		return "/proveedores/MostrarProveedores.jsp";
	}
}
