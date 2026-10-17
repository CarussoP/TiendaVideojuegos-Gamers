package mx.com.cursodia.javaEE2022.dao.interfaces;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de escritura de plataformas. */
public interface IPlataformaComandoDAO
{
	void insertar(Plataforma plataforma) throws DataBaseException;

	void actualizar(Plataforma plataforma) throws DataBaseException;

	boolean borrar(int id) throws DataBaseException;
}
