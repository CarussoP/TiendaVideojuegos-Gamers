package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.dao.DaoFactory;
import mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoConsultaDAO;
import mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoComandoDAO;

public class ModificarVideojuegoAccion extends AccionVideojuegos
{
	private final IVideojuegoCatalogoConsultaDAO catalogoConsultaDAO = DaoFactory.getVideojuegoCatalogoConsultaDAO();
	private final IProveedorConsultaDAO proveedorConsultaDAO = DaoFactory.getProveedorConsultaDAO();
	private final IVideojuegoComandoDAO videojuegoComandoDAO = DaoFactory.getVideojuegoComandoDAO();

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("VJ"));
			VideojuegoCatalogo catalogo = catalogoConsultaDAO.buscarPorId(Integer.parseInt(request.getParameter("CATVID")));
			Proveedor proveedor = proveedorConsultaDAO.buscarPorId(Integer.parseInt(request.getParameter("CATPROV")));
			float precioCompra = Float.parseFloat(request.getParameter("PCOMPRA"));
			float precioVenta = Float.parseFloat(request.getParameter("PVENTA"));
			int cantidad = Integer.parseInt(request.getParameter("CANT"));
			Videojuego oferta = new Videojuego(catalogo, proveedor, precioCompra, precioVenta, cantidad);
			oferta.setVj_id(id);
			videojuegoComandoDAO.actualizar(oferta);
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarVideojuegos.do";
	}
}
