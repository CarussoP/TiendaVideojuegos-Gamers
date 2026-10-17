package mx.com.cursodia.javaEE2022.dao.interfaces;

import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de escritura del catálogo de títulos. */
public interface IVideojuegoCatalogoComandoDAO
{
	void insertar(VideojuegoCatalogo catalogo) throws DataBaseException;

	void actualizar(VideojuegoCatalogo catalogo) throws DataBaseException;

	boolean borrar(int id) throws DataBaseException;
}
