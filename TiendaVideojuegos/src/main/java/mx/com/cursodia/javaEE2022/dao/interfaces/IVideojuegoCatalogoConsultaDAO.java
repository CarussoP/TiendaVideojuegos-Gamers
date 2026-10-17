package mx.com.cursodia.javaEE2022.dao.interfaces;

import java.util.List;

import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de lectura del catálogo de títulos. */
public interface IVideojuegoCatalogoConsultaDAO
{
	List<VideojuegoCatalogo> buscarTodos() throws DataBaseException;

	VideojuegoCatalogo buscarPorId(int id) throws DataBaseException;
}
