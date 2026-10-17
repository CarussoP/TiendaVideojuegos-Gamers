package mx.com.cursodia.javaEE2022.acciones.asignaciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.ProveedorPlataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorPlataformaComandoDAO;

public class InsertarAsignacionAccion extends AccionAsignaciones
{
	private final IProveedorConsultaDAO proveedorConsultaDAO = DaoFactory.getProveedorConsultaDAO();
	private final IPlataformaConsultaDAO plataformaConsultaDAO = DaoFactory.getPlataformaConsultaDAO();
	private final IProveedorPlataformaComandoDAO asignacionComandoDAO = DaoFactory.getProveedorPlataformaComandoDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			Proveedor proveedor = proveedorConsultaDAO.buscarPorId(Integer.parseInt(request.getParameter("PROV")));
			Plataforma plataforma = plataformaConsultaDAO.buscarPorId(Integer.parseInt(request.getParameter("PLA")));
			asignacionComandoDAO.insertar(new ProveedorPlataforma(proveedor, plataforma));
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarAsignaciones.asig";
	}
}
