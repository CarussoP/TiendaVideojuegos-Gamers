package mx.com.cursodia.javaEE2022.controlador.proveedores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.acciones.Accion;
import mx.com.cursodia.javaEE2022.acciones.AccionException;
import mx.com.cursodia.javaEE2022.acciones.proveedores.AccionProveedores;

/**
 * Front Controller del dominio Proveedores (mapeado a *.pro).
 */
public class ControladorProveedores extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		procesar(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		procesar(request, response);
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Accion accion = AccionProveedores.getAccion(request.getServletPath());
			RequestDispatcher despachador = request.getRequestDispatcher(accion.ejecutar(request, response));
			despachador.forward(request, response);
		}
		catch(AccionException e)
		{
			request.setAttribute("mensajeError", e.getMessage());
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}
}
