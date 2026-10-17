package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoConsultaDAO;

/**
 * LA REGLA DE DECISION: dado un titulo del catalogo y una cantidad
 * requerida, muestra que proveedores pueden surtirla (tienen stock
 * suficiente Y estan autorizados para esa plataforma).
 */
public class BuscarProveedoresParaSurtirAccion extends AccionVideojuegos
{
	private final IVideojuegoCatalogoConsultaDAO catalogoConsultaDAO = DaoFactory.getVideojuegoCatalogoConsultaDAO();
	private final IVideojuegoConsultaDAO videojuegoConsultaDAO = DaoFactory.getVideojuegoConsultaDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			request.setAttribute("listaDeCatalogo", catalogoConsultaDAO.buscarTodos());

			String catVidParam = request.getParameter("CATVID");
			String cantParam = request.getParameter("CANT");
			if(catVidParam != null && !catVidParam.isEmpty() && cantParam != null && !cantParam.isEmpty())
			{
				int catVidId = Integer.parseInt(catVidParam);
				int cantidad = Integer.parseInt(cantParam);
				List<Videojuego> proveedoresQuePuedenSurtir =
						videojuegoConsultaDAO.buscarProveedoresQuePuedenSurtir(catVidId, cantidad);
				request.setAttribute("proveedoresQuePuedenSurtir", proveedoresQuePuedenSurtir);
				request.setAttribute("catVidIdConsultado", catVidId);
				request.setAttribute("cantidadConsultada", cantidad);
			}
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/videojuegos/BuscarProveedores.jsp";
	}
}
