package mx.com.cursodia.javaEE2022.dao.interfaces;

import java.util.List;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de lectura de ofertas (Videojuego). */
public interface IVideojuegoConsultaDAO
{
	List<Videojuego> buscarTodos() throws DataBaseException;

	Videojuego buscarPorId(int id) throws DataBaseException;

	List<Videojuego> filtrarPorProveedor(int catProvId) throws DataBaseException;

	/**
	 * Regla de decisión: ofertas con stock suficiente y proveedor autorizado
	 * para la plataforma del título.
	 */
	List<Videojuego> buscarProveedoresQuePuedenSurtir(int catVidId, int cantidadRequerida)
			throws DataBaseException;
}
