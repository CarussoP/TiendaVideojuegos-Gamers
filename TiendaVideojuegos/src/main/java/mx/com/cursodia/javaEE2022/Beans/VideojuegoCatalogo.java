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
 * Catalogo de titulos. Entidad JPA → tabla cat_videojuegos.
 * Distinto de {@link Videojuego} (oferta de un proveedor).
 * Persistencia: {@link mx.com.cursodia.javaEE2022.dao.interfaces.IVideojuegoCatalogoDAO}.
 */
@Entity
@Table(name = "cat_videojuegos")
public class VideojuegoCatalogo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_vid_id")
	private int cat_vid_id;

	@Column(name = "cat_vid_nombre")
	private String cat_vid_nombre;

	/** EAGER: los JSP leen plataforma fuera del EntityManager. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cat_vid_pla_id")
	private Plataforma plataforma;

	@Column(name = "cat_vid_inventario")
	private int cat_vid_inventario;

	public VideojuegoCatalogo() {}

	public VideojuegoCatalogo(String cat_vid_nombre, Plataforma plataforma, int cat_vid_inventario)
	{
		this.cat_vid_nombre = cat_vid_nombre;
		this.plataforma = plataforma;
		this.cat_vid_inventario = cat_vid_inventario;
	}

	public int getCat_vid_id() { return cat_vid_id; }
	public void setCat_vid_id(int cat_vid_id) { this.cat_vid_id = cat_vid_id; }

	public String getCat_vid_nombre() { return cat_vid_nombre; }
	public void setCat_vid_nombre(String cat_vid_nombre) { this.cat_vid_nombre = cat_vid_nombre; }

	public Plataforma getPlataforma() { return plataforma; }
	public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

	public int getCat_vid_inventario() { return cat_vid_inventario; }
	public void setCat_vid_inventario(int cat_vid_inventario) { this.cat_vid_inventario = cat_vid_inventario; }

	@Override
	public String toString()
	{
		return "cat_vid_id: " + cat_vid_id + ", cat_vid_nombre: " + cat_vid_nombre
				+ ", plataforma: " + (plataforma != null ? plataforma.getCat_pla_nombre() : "N/A")
				+ ", cat_vid_inventario: " + cat_vid_inventario;
	}
}
