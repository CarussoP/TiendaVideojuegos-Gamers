package mx.com.cursodia.javaEE2022.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catalogo de proveedores. Entidad JPA → tabla cat_proveedores.
 * Persistencia: {@link mx.com.cursodia.javaEE2022.dao.interfaces.IProveedorDAO}.
 */
@Entity
@Table(name = "cat_proveedores")
public class Proveedor
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_prov_id")
	private int cat_prov_id;

	@Column(name = "cat_prov_nombre")
	private String cat_prov_nombre;

	@Column(name = "cat_prov_tel")
	private String cat_prov_tel;

	@Column(name = "cat_prov_correo")
	private String cat_prov_correo;

	@Column(name = "cat_prov_direccion")
	private String cat_prov_direccion;

	public Proveedor() {}

	public Proveedor(String cat_prov_nombre, String cat_prov_tel, String cat_prov_correo, String cat_prov_direccion)
	{
		this.cat_prov_nombre = cat_prov_nombre;
		this.cat_prov_tel = cat_prov_tel;
		this.cat_prov_correo = cat_prov_correo;
		this.cat_prov_direccion = cat_prov_direccion;
	}

	public int getCat_prov_id() { return cat_prov_id; }
	public void setCat_prov_id(int cat_prov_id) { this.cat_prov_id = cat_prov_id; }

	public String getCat_prov_nombre() { return cat_prov_nombre; }
	public void setCat_prov_nombre(String cat_prov_nombre) { this.cat_prov_nombre = cat_prov_nombre; }

	public String getCat_prov_tel() { return cat_prov_tel; }
	public void setCat_prov_tel(String cat_prov_tel) { this.cat_prov_tel = cat_prov_tel; }

	public String getCat_prov_correo() { return cat_prov_correo; }
	public void setCat_prov_correo(String cat_prov_correo) { this.cat_prov_correo = cat_prov_correo; }

	public String getCat_prov_direccion() { return cat_prov_direccion; }
	public void setCat_prov_direccion(String cat_prov_direccion) { this.cat_prov_direccion = cat_prov_direccion; }

	@Override
	public String toString()
	{
		return "cat_prov_id: " + cat_prov_id + ", cat_prov_nombre: " + cat_prov_nombre + ", cat_prov_tel: " + cat_prov_tel
				+ ", cat_prov_correo: " + cat_prov_correo + ", cat_prov_direccion: " + cat_prov_direccion;
	}
}
