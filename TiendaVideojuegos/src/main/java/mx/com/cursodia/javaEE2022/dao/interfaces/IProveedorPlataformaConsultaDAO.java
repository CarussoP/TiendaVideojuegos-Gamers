package mx.com.cursodia.javaEE2022.dao.interfaces;

import java.util.List;

import mx.com.cursodia.javaEE2022.Beans.ProveedorPlataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de lectura de asignaciones proveedor–plataforma. */
public interface IProveedorPlataformaConsultaDAO
{
	List<ProveedorPlataforma> buscarTodas() throws DataBaseException;
}
