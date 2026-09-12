package mx.com.cursodia.javaEE2022.api.ofertas;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.com.cursodia.javaEE2022.Beans.Plataforma;
import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;

/**
 * Convierte entidades Hibernate a Maps planos (sin proxies/ciclos) para JSON.
 * Los nombres de campos alinean con los modelos TypeScript de Gamers_FrontEnd.
 */
public final class OfertaDtoMapper
{
	private OfertaDtoMapper() {}

	public static Map<String, Object> fromOferta(Videojuego oferta)
	{
		Map<String, Object> dto = new LinkedHashMap<>();
		dto.put("vj_id", oferta.getVj_id());
		dto.put("vj_precio_compra", oferta.getVj_precio_compra());
		dto.put("vj_precio_venta", oferta.getVj_precio_venta());
		dto.put("vj_cantidad", oferta.getVj_cantidad());
		dto.put("videojuegoCatalogo", fromCatalogo(oferta.getVideojuegoCatalogo()));
		dto.put("proveedor", fromProveedor(oferta.getProveedor()));
		return dto;
	}

	public static Map<String, Object> fromCatalogo(VideojuegoCatalogo catalogo)
	{
		Map<String, Object> dto = new LinkedHashMap<>();
		if(catalogo == null)
		{
			return dto;
		}
		dto.put("cat_vid_id", catalogo.getCat_vid_id());
		dto.put("cat_vid_nombre", catalogo.getCat_vid_nombre());
		dto.put("plataforma", fromPlataforma(catalogo.getPlataforma()));
		return dto;
	}

	public static Map<String, Object> fromPlataforma(Plataforma plataforma)
	{
		Map<String, Object> dto = new LinkedHashMap<>();
		if(plataforma == null)
		{
			return dto;
		}
		dto.put("cat_pla_id", plataforma.getCat_pla_id());
		dto.put("cat_pla_nombre", plataforma.getCat_pla_nombre());
		dto.put("cat_pla_compania", plataforma.getCat_pla_compania());
		return dto;
	}

	public static Map<String, Object> fromProveedor(Proveedor proveedor)
	{
		Map<String, Object> dto = new LinkedHashMap<>();
		if(proveedor == null)
		{
			return dto;
		}
		dto.put("cat_prov_id", proveedor.getCat_prov_id());
		dto.put("cat_prov_nombre", proveedor.getCat_prov_nombre());
		dto.put("cat_prov_tel", proveedor.getCat_prov_tel());
		dto.put("cat_prov_correo", proveedor.getCat_prov_correo());
		dto.put("cat_prov_direccion", proveedor.getCat_prov_direccion());
		return dto;
	}

	public static List<Map<String, Object>> fromOfertas(List<Videojuego> ofertas)
	{
		List<Map<String, Object>> lista = new ArrayList<>();
		if(ofertas == null)
		{
			return lista;
		}
		for(Videojuego oferta : ofertas)
		{
			lista.add(fromOferta(oferta));
		}
		return lista;
	}

	public static List<Map<String, Object>> fromProveedores(List<Proveedor> proveedores)
	{
		List<Map<String, Object>> lista = new ArrayList<>();
		if(proveedores == null)
		{
			return lista;
		}
		for(Proveedor proveedor : proveedores)
		{
			lista.add(fromProveedor(proveedor));
		}
		return lista;
	}

	public static List<Map<String, Object>> fromCatalogos(List<VideojuegoCatalogo> catalogos)
	{
		List<Map<String, Object>> lista = new ArrayList<>();
		if(catalogos == null)
		{
			return lista;
		}
		for(VideojuegoCatalogo catalogo : catalogos)
		{
			lista.add(fromCatalogo(catalogo));
		}
		return lista;
	}
}
