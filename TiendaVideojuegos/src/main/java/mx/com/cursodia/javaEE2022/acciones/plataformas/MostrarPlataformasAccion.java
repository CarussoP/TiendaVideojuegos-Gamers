package mx.com.cursodia.javaEE2022.acciones.plataformas;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaConsultaDAO;

public class MostrarPlataformasAccion extends AccionPlataformas
{
	private final IPlataformaConsultaDAO plataformaConsultaDAO = DaoFactory.getPlataformaConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		List<Plataforma> listaDePlataformas = null;
		try
		{
			listaDePlataformas = plataformaConsultaDAO.buscarTodas();
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
		}
		request.setAttribute("listaDePlataformas", listaDePlataformas);
		return "/plataformas/MostrarPlataformas.jsp";
	}
}
