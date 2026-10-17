package mx.com.cursodia.javaEE2022.acciones.plataformas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaConsultaDAO;

public class FormularioEditarPlataformaAccion extends AccionPlataformas
{
	private final IPlataformaConsultaDAO plataformaConsultaDAO = DaoFactory.getPlataformaConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("PLA"));
			request.setAttribute("plataforma", plataformaConsultaDAO.buscarPorId(id));
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/plataformas/FormularioPlataforma.jsp";
	}
}
