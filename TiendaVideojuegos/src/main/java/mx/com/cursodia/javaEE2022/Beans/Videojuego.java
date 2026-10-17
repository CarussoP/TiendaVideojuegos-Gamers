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
 * Oferta de un proveedor sobre un titulo. Entidad JPA → tabla videojuego.
 * Persistencia: {@link mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoDAO}.
 */
@Entity
@Table(name = "videojuego")
public class Videojuego
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vj_id")
	private int vj_id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "vj_cat_vid_id", nullable = false)
	private VideojuegoCatalogo videojuegoCatalogo;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "vj_cat_prov_id", nullable = false)
	private Proveedor proveedor;

	@Column(name = "vj_precio_compra")
	private float vj_precio_compra;

	@Column(name = "vj_precio_venta")
	private float vj_precio_venta;

	@Column(name = "vj_cantidad")
	private int vj_cantidad;

	public Videojuego() {}

	public Videojuego(VideojuegoCatalogo videojuegoCatalogo, Proveedor proveedor,
			float vj_precio_compra, float vj_precio_venta, int vj_cantidad)
	{
		this.videojuegoCatalogo = videojuegoCatalogo;
		this.proveedor = proveedor;
		this.vj_precio_compra = vj_precio_compra;
		this.vj_precio_venta = vj_precio_venta;
		this.vj_cantidad = vj_cantidad;
	}

	public int getVj_id() { return vj_id; }
	public void setVj_id(int vj_id) { this.vj_id = vj_id; }

	public VideojuegoCatalogo getVideojuegoCatalogo() { return videojuegoCatalogo; }
	public void setVideojuegoCatalogo(VideojuegoCatalogo videojuegoCatalogo) { this.videojuegoCatalogo = videojuegoCatalogo; }

	public Proveedor getProveedor() { return proveedor; }
	public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

	public float getVj_precio_compra() { return vj_precio_compra; }
	public void setVj_precio_compra(float vj_precio_compra) { this.vj_precio_compra = vj_precio_compra; }

	public float getVj_precio_venta() { return vj_precio_venta; }
	public void setVj_precio_venta(float vj_precio_venta) { this.vj_precio_venta = vj_precio_venta; }

	public int getVj_cantidad() { return vj_cantidad; }
	public void setVj_cantidad(int vj_cantidad) { this.vj_cantidad = vj_cantidad; }

	@Override
	public String toString()
	{
		return "vj_id: " + vj_id
				+ ", titulo: " + (videojuegoCatalogo != null ? videojuegoCatalogo.getCat_vid_nombre() : "N/A")
				+ ", proveedor: " + (proveedor != null ? proveedor.getCat_prov_nombre() : "N/A")
				+ ", vj_precio_compra: " + vj_precio_compra
				+ ", vj_precio_venta: " + vj_precio_venta
				+ ", vj_cantidad: " + vj_cantidad;
	}
}
