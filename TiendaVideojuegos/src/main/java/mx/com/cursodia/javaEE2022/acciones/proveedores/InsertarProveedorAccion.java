package mx.com.cursodia.javaEE2022.acciones.proveedores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorComandoDAO;

public class InsertarProveedorAccion extends AccionProveedores
{
	private final IProveedorComandoDAO proveedorComandoDAO = DaoFactory.getProveedorComandoDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String nombre = request.getParameter("NOM");
			String tel = request.getParameter("TEL");
			String correo = request.getParameter("MAIL");
			String direccion = request.getParameter("DIR");
			proveedorComandoDAO.insertar(new Proveedor(nombre, tel, correo, direccion));
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		return "/MostrarProveedores.pro";
	}
}
