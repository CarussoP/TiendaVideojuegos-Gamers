package mx.com.cursodia.javaEE2022.controlador.plataformas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.acciones.Accion;
import mx.com.cursodia.javaEE2022.acciones.AccionException;
import mx.com.cursodia.javaEE2022.acciones.plataformas.AccionPlataformas;

/**
 * Front Controller del dominio Plataformas (mapeado a *.pla).
 */
public class ControladorPlataformas extends HttpServlet
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
			Accion accion = AccionPlataformas.getAccion(request.getServletPath());
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
