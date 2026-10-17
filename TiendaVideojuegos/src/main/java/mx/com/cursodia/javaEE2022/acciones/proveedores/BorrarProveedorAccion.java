package mx.com.cursodia.javaEE2022.acciones.proveedores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorComandoDAO;

public class BorrarProveedorAccion extends AccionProveedores
{
	private final IProveedorComandoDAO proveedorComandoDAO = DaoFactory.getProveedorComandoDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("PROV"));
			proveedorComandoDAO.borrar(id);
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarProveedores.pro";
	}
}
