package mx.com.cursodia.javaEE2022.acciones.videojuegos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

public class ModificarVideojuegoAccion extends AccionVideojuegos
{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("VJ"));
			VideojuegoCatalogo catalogo = VideojuegoCatalogo.buscarPorId(Integer.parseInt(request.getParameter("CATVID")));
			Proveedor proveedor = Proveedor.buscarPorId(Integer.parseInt(request.getParameter("CATPROV")));
			float precioCompra = Float.parseFloat(request.getParameter("PCOMPRA"));
			float precioVenta = Float.parseFloat(request.getParameter("PVENTA"));
			int cantidad = Integer.parseInt(request.getParameter("CANT"));
			Videojuego oferta = new Videojuego(catalogo, proveedor, precioCompra, precioVenta, cantidad);
			oferta.setVj_id(id);
			Videojuego.actualizar(oferta);
		}
		catch(DataBaseException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		return "/MostrarVideojuegos.do";
	}
}
