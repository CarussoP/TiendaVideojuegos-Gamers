package mx.com.cursodia.javaEE2022.dao.interfaces;

import mx.com.cursodia.javaEE2022.Beans.ProveedorPlataforma;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de escritura de asignaciones proveedor–plataforma. */
public interface IProveedorPlataformaComandoDAO
{
	void insertar(ProveedorPlataforma asignacion) throws DataBaseException;

	boolean borrar(int id) throws DataBaseException;
}
