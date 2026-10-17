package mx.com.cursodia.javaEE2022.dao.interfaces;

import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de escritura de ofertas (Videojuego). */
public interface IVideojuegoComandoDAO
{
	/** Inserta la oferta; el ID lo genera la BD. */
	int insertar(Videojuego oferta) throws DataBaseException;

	void actualizar(Videojuego oferta) throws DataBaseException;

	boolean borrar(int id) throws DataBaseException;
}
