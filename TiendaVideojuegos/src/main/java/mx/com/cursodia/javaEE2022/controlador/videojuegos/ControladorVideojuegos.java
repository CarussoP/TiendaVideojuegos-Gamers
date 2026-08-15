package mx.com.cursodia.javaEE2022.controlador.videojuegos;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.cursodia.javaEE2022.acciones.Accion;
import mx.com.cursodia.javaEE2022.acciones.AccionException;
import mx.com.cursodia.javaEE2022.acciones.videojuegos.AccionVideojuegos;

/**
 * Front Controller del dominio Videojuegos (mapeado a *.do). Solo conoce
 * AccionVideojuegos.getAccion(...): nunca importa una clase concreta de
 * acciones.videojuegos, por eso agregar una accion nueva (OCP) no lo toca.
 */
public class ControladorVideojuegos extends HttpServlet
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
			Accion accion = AccionVideojuegos.getAccion(request.getServletPath());
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
