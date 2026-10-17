package mx.com.cursodia.javaEE2022.dao.interfaces;

import java.util.List;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de lectura de plataformas. */
public interface IPlataformaConsultaDAO
{
	List<Plataforma> buscarTodas() throws DataBaseException;

	Plataforma buscarPorId(int id) throws DataBaseException;
}
