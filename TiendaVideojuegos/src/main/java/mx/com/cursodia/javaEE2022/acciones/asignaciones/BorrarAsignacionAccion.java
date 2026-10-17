package mx.com.cursodia.javaEE2022.acciones.asignaciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorPlataformaComandoDAO;

public class BorrarAsignacionAccion extends AccionAsignaciones
{
	private final IProveedorPlataformaComandoDAO asignacionComandoDAO = DaoFactory.getProveedorPlataformaComandoDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("PP"));
			asignacionComandoDAO.borrar(id);
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarAsignaciones.asig";
	}
}
