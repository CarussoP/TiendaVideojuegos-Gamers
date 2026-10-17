package mx.com.cursodia.javaEE2022.acciones.proveedores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorConsultaDAO;

public class MostrarProveedoresAccion extends AccionProveedores
{
	private final IProveedorConsultaDAO proveedorConsultaDAO = DaoFactory.getProveedorConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		List<Proveedor> listaDeProveedores = null;
		try
		{
			listaDeProveedores = proveedorConsultaDAO.buscarTodos();
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		request.setAttribute("listaDeProveedores", listaDeProveedores);
		return "/proveedores/MostrarProveedores.jsp";
	}
}
