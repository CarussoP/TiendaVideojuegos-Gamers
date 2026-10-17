package mx.com.cursodia.javaEE2022.dao.interfaces;

import java.util.List;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de lectura de proveedores. */
public interface IProveedorConsultaDAO
{
	List<Proveedor> buscarTodos() throws DataBaseException;

	Proveedor buscarPorId(int id) throws DataBaseException;
}
