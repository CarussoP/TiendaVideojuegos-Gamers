package mx.com.cursodia.javaEE2022.acciones.proveedores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorComandoDAO;

public class ModificarProveedorAccion extends AccionProveedores
{
	private final IProveedorComandoDAO proveedorComandoDAO = DaoFactory.getProveedorComandoDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("PROV"));
			Proveedor proveedor = new Proveedor(request.getParameter("NOM"), request.getParameter("TEL"),
					request.getParameter("MAIL"), request.getParameter("DIR"));
			proveedor.setCat_prov_id(id);
			proveedorComandoDAO.actualizar(proveedor);
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarProveedores.pro";
	}
}
