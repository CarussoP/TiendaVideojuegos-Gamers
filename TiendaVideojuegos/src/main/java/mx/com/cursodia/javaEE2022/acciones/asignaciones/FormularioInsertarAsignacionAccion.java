package mx.com.cursodia.javaEE2022.acciones.asignaciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorConsultaDAO;

public class FormularioInsertarAsignacionAccion extends AccionAsignaciones
{
	private final IProveedorConsultaDAO proveedorConsultaDAO = DaoFactory.getProveedorConsultaDAO();
	private final IPlataformaConsultaDAO plataformaConsultaDAO = DaoFactory.getPlataformaConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			request.setAttribute("listaDeProveedores", proveedorConsultaDAO.buscarTodos());
			request.setAttribute("listaDePlataformas", plataformaConsultaDAO.buscarTodas());
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		return "/asignaciones/FormularioAsignacion.jsp";
	}
}
