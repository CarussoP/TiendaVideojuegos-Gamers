package mx.com.cursodia.javaEE2022.api.ofertas;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import mx.com.cursodia.javaEE2022.Beans.Proveedor;
import mx.com.cursodia.javaEE2022.Beans.Videojuego;
import mx.com.cursodia.javaEE2022.Beans.VideojuegoCatalogo;
import mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException;
import mx.com.cursodia.javaEE2022.api.JsonUtil;

/**
 * API JSON CRUD del modulo Ofertas.
 *
 * GET    /api/ofertas                 — lista (+ proveedores para filtro)
 * GET    /api/ofertas?proveedor={id}  — filtra por proveedor
 * GET    /api/ofertas/form-data       — catalogo + proveedores (formulario)
 * GET    /api/ofertas/{id}            — detalle
 * POST   /api/ofertas                 — crear
 * PUT    /api/ofertas/{id}            — actualizar
 * DELETE /api/ofertas/{id}            — borrar
 *
 * Body POST/PUT:
 * {
 *   "cat_vid_id": 1,
 *   "cat_prov_id": 2,
 *   "vj_precio_compra": 100.0,
 *   "vj_precio_venta": 150.0,
 *   "vj_cantidad": 10
 * }
 */
public class OfertasApiServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String path = pathInfo(request);
		try
		{
			if(path == null || path.isEmpty() || "/".equals(path))
			{
				listar(request, response);
				return;
			}
			if("/form-data".equals(path))
			{
				formData(response);
				return;
			}
			Integer id = parseId(path);
			if(id != null)
			{
				detalle(id, response);
				return;
			}
			JsonUtil.writeError(response, HttpServletResponse.SC_NOT_FOUND, "Ruta no encontrada: " + path);
		}
		catch(NumberFormatException e)
		{
			JsonUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Id invalido en la ruta");
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
			JsonUtil.writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Error de base de datos: " + e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String path = pathInfo(request);
		if(path != null && !path.isEmpty() && !"/".equals(path))
		{
			JsonUtil.writeError(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED,
					"POST solo se admite en /api/ofertas");
			return;
		}
		try
		{
			JsonObject body = JsonUtil.readJsonObject(request);
			Videojuego oferta = buildOfertaFromBody(body, null);
			int nuevoId = (int) Videojuego.insertar(oferta);
			Videojuego creada = Videojuego.buscarPorId(nuevoId);
			JsonUtil.writeJson(response, HttpServletResponse.SC_CREATED, OfertaDtoMapper.fromOferta(creada));
		}
		catch(IllegalArgumentException | JsonSyntaxException e)
		{
			JsonUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
			JsonUtil.writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Error al crear oferta: " + e.getMessage());
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			Integer id = parseId(pathInfo(request));
			if(id == null)
			{
				JsonUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST,
						"PUT requiere /api/ofertas/{id}");
				return;
			}
			Videojuego existente = Videojuego.buscarPorId(id);
			if(existente == null)
			{
				JsonUtil.writeError(response, HttpServletResponse.SC_NOT_FOUND,
						"Oferta no encontrada: " + id);
				return;
			}
			JsonObject body = JsonUtil.readJsonObject(request);
			Videojuego oferta = buildOfertaFromBody(body, id);
			Videojuego.actualizar(oferta);
			Videojuego actualizada = Videojuego.buscarPorId(id);
			JsonUtil.writeJson(response, HttpServletResponse.SC_OK, OfertaDtoMapper.fromOferta(actualizada));
		}
		catch(IllegalArgumentException | JsonSyntaxException e)
		{
			JsonUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
			JsonUtil.writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Error al actualizar oferta: " + e.getMessage());
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			Integer id = parseId(pathInfo(request));
			if(id == null)
			{
				JsonUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST,
						"DELETE requiere /api/ofertas/{id}");
				return;
			}
			boolean borrada = Videojuego.borrar(id);
			if(!borrada)
			{
				JsonUtil.writeError(response, HttpServletResponse.SC_NOT_FOUND,
						"Oferta no encontrada: " + id);
				return;
			}
			Map<String, Object> ok = new LinkedHashMap<>();
			ok.put("ok", true);
			ok.put("vj_id", id);
			JsonUtil.writeJson(response, HttpServletResponse.SC_OK, ok);
		}
		catch(NumberFormatException e)
		{
			JsonUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Id invalido en la ruta");
		}
		catch(DataBaseException e)
		{
			e.printStackTrace();
			JsonUtil.writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Error al borrar oferta: " + e.getMessage());
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws DataBaseException, IOException
	{
		String proveedorParam = request.getParameter("proveedor");
		List<Videojuego> ofertas;
		if(proveedorParam == null
				|| proveedorParam.isBlank()
				|| "Seleccionar".equalsIgnoreCase(proveedorParam)
				|| "Todos".equalsIgnoreCase(proveedorParam))
		{
			ofertas = Videojuego.buscarTodos();
		}
		else
		{
			ofertas = Videojuego.filtrarPorProveedor(Integer.parseInt(proveedorParam));
		}

		List<Proveedor> proveedores = Proveedor.buscarTodos();
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("ofertas", OfertaDtoMapper.fromOfertas(ofertas));
		body.put("proveedores", OfertaDtoMapper.fromProveedores(proveedores));
		JsonUtil.writeJson(response, HttpServletResponse.SC_OK, body);
	}

	private void formData(HttpServletResponse response) throws DataBaseException, IOException
	{
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("catalogo", OfertaDtoMapper.fromCatalogos(VideojuegoCatalogo.buscarTodos()));
		body.put("proveedores", OfertaDtoMapper.fromProveedores(Proveedor.buscarTodos()));
		JsonUtil.writeJson(response, HttpServletResponse.SC_OK, body);
	}

	private void detalle(int id, HttpServletResponse response) throws DataBaseException, IOException
	{
		Videojuego oferta = Videojuego.buscarPorId(id);
		if(oferta == null)
		{
			JsonUtil.writeError(response, HttpServletResponse.SC_NOT_FOUND, "Oferta no encontrada: " + id);
			return;
		}
		JsonUtil.writeJson(response, HttpServletResponse.SC_OK, OfertaDtoMapper.fromOferta(oferta));
	}

	private Videojuego buildOfertaFromBody(JsonObject body, Integer idForUpdate)
			throws DataBaseException
	{
		Integer catVidId = JsonUtil.getInt(body, "cat_vid_id");
		Integer catProvId = JsonUtil.getInt(body, "cat_prov_id");
		Float precioCompra = JsonUtil.getFloat(body, "vj_precio_compra");
		Float precioVenta = JsonUtil.getFloat(body, "vj_precio_venta");
		Integer cantidad = JsonUtil.getInt(body, "vj_cantidad");

		if(catVidId == null || catProvId == null || precioCompra == null || precioVenta == null || cantidad == null)
		{
			throw new IllegalArgumentException(
					"Faltan campos requeridos: cat_vid_id, cat_prov_id, vj_precio_compra, vj_precio_venta, vj_cantidad");
		}
		if(cantidad < 0 || precioCompra < 0 || precioVenta < 0)
		{
			throw new IllegalArgumentException("Precios y cantidad no pueden ser negativos");
		}

		VideojuegoCatalogo catalogo = VideojuegoCatalogo.buscarPorId(catVidId);
		if(catalogo == null)
		{
			throw new IllegalArgumentException("Catalogo no encontrado: " + catVidId);
		}
		Proveedor proveedor = Proveedor.buscarPorId(catProvId);
		if(proveedor == null)
		{
			throw new IllegalArgumentException("Proveedor no encontrado: " + catProvId);
		}

		Videojuego oferta = new Videojuego(catalogo, proveedor, precioCompra, precioVenta, cantidad);
		if(idForUpdate != null)
		{
			oferta.setVj_id(idForUpdate);
		}
		return oferta;
	}

	private static String pathInfo(HttpServletRequest request)
	{
		String pathInfo = request.getPathInfo();
		if(pathInfo != null)
		{
			return pathInfo;
		}
		// Cuando el mapping es exacto /api/ofertas, pathInfo es null.
		return null;
	}

	/** Acepta "/12" o "12". */
	private static Integer parseId(String path)
	{
		if(path == null || path.isBlank() || "/".equals(path) || "/form-data".equals(path))
		{
			return null;
		}
		String raw = path.startsWith("/") ? path.substring(1) : path;
		if(raw.contains("/"))
		{
			return null;
		}
		return Integer.valueOf(raw);
	}
}
