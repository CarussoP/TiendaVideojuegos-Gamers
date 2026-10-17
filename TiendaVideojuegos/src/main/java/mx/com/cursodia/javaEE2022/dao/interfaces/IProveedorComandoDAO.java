package mx.com.cursodia.javaEE2022.dao.interfaces;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;

/** ISP: solo operaciones de escritura de proveedores. */
public interface IProveedorComandoDAO
{
	void insertar(Proveedor proveedor) throws DataBaseException;

	void actualizar(Proveedor proveedor) throws DataBaseException;

	boolean borrar(int id) throws DataBaseException;
}
