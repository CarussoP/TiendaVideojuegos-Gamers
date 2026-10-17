package mx.com.cursodia.javaEE2022.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Relacion N:M proveedor–plataforma. Entidad JPA → proveedores_plataformas.
 * Persistencia: {@link mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorPlataformaDAO}.
 */
@Entity
@Table(name = "proveedores_plataformas")
public class ProveedorPlataforma
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pp_id")
	private int pp_id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "pp_cat_prov_id", nullable = false)
	private Proveedor proveedor;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "pp_cat_pla_id", nullable = false)
	private Plataforma plataforma;

	public ProveedorPlataforma() {}

	public ProveedorPlataforma(Proveedor proveedor, Plataforma plataforma)
	{
		this.proveedor = proveedor;
		this.plataforma = plataforma;
	}

	public int getPp_id() { return pp_id; }
	public void setPp_id(int pp_id) { this.pp_id = pp_id; }

	public Proveedor getProveedor() { return proveedor; }
	public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

	public Plataforma getPlataforma() { return plataforma; }
	public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

	@Override
	public String toString()
	{
		return "pp_id: " + pp_id
				+ ", proveedor: " + (proveedor != null ? proveedor.getCat_prov_nombre() : "N/A")
				+ ", plataforma: " + (plataforma != null ? plataforma.getCat_pla_nombre() : "N/A");
	}
}
