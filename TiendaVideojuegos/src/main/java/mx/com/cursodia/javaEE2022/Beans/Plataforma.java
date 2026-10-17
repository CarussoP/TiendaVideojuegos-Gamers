package mx.com.cursodia.javaEE2022.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catalogo de plataformas. Entidad JPA → tabla cat_plataformas.
 * Persistencia: {@link mx.com.cursodia.javaEE2022.dao.interfaces.IPlataformaDAO}.
 */
@Entity
@Table(name = "cat_plataformas")
public class Plataforma
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_pla_id")
	private int cat_pla_id;

	@Column(name = "cat_pla_nombre")
	private String cat_pla_nombre;

	@Column(name = "cat_pla_compania")
	private String cat_pla_compania;

	public Plataforma() {}

	public Plataforma(String cat_pla_nombre, String cat_pla_compania)
	{
		this.cat_pla_nombre = cat_pla_nombre;
		this.cat_pla_compania = cat_pla_compania;
	}

	public int getCat_pla_id() { return cat_pla_id; }
	public void setCat_pla_id(int cat_pla_id) { this.cat_pla_id = cat_pla_id; }

	public String getCat_pla_nombre() { return cat_pla_nombre; }
	public void setCat_pla_nombre(String cat_pla_nombre) { this.cat_pla_nombre = cat_pla_nombre; }

	public String getCat_pla_compania() { return cat_pla_compania; }
	public void setCat_pla_compania(String cat_pla_compania) { this.cat_pla_compania = cat_pla_compania; }

	@Override
	public String toString()
	{
		return "cat_pla_id: " + cat_pla_id + ", cat_pla_nombre: " + cat_pla_nombre + ", cat_pla_compania: " + cat_pla_compania;
	}
}
