package mx.com.cursodia.javaEE2022.dao.interfaces;

/**
 * Contrato completo de persistencia de Proveedor.
 * Combina consulta y comando; los clientes deben depender de la interfaz
 * segregada que realmente necesiten (ISP).
 */
public interface IProveedorDAO extends IProveedorConsultaDAO, IProveedorComandoDAO
{
}
