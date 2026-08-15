<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Ofertas de Videojuegos</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<h2>Ofertas (proveedor x titulo)</h2>

	<form name="filtroProveedor" action="<%=request.getContextPath()%>/FiltrarVideojuegosPorProveedor.do" method="GET" class="row row-cols-auto g-2 align-items-center mb-3">
		<div class="col">
			<select name="Proveedor" class="form-select">
				<option value="Seleccionar">Todos los proveedores</option>
				<c:forEach var="p" items="${listaDeProveedores}">
					<option value="${p.cat_prov_id}">${p.cat_prov_nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col">
			<input type="submit" value="Filtrar" class="btn btn-secondary">
		</div>
	</form>

	<table class="table table-striped" cellspacing="15">
		<tr>
			<th>ID</th>
			<th>Titulo</th>
			<th>Plataforma</th>
			<th>Proveedor</th>
			<th>Precio compra</th>
			<th>Precio venta</th>
			<th>Cantidad</th>
			<th colspan="2"></th>
		</tr>
		<c:forEach var="oferta" items="${listaDeOfertas}">
			<tr>
				<td>${oferta.vj_id}</td>
				<td>${oferta.videojuegoCatalogo.cat_vid_nombre}</td>
				<td>${oferta.videojuegoCatalogo.plataforma.cat_pla_nombre}</td>
				<td>${oferta.proveedor.cat_prov_nombre}</td>
				<td>${oferta.vj_precio_compra}</td>
				<td>${oferta.vj_precio_venta}</td>
				<td>${oferta.vj_cantidad}</td>
				<td><input type="button" class="btn btn-sm btn-primary" value="Editar" onclick="location.href='<%=request.getContextPath()%>/FormularioEditarVideojuego.do?VJ=${oferta.vj_id}'"/></td>
				<td><input type="button" class="btn btn-sm btn-danger" value="Borrar" onclick="location.href='<%=request.getContextPath()%>/BorrarVideojuego.do?VJ=${oferta.vj_id}'"/></td>
			</tr>
		</c:forEach>
	</table>

	<a href="<%=request.getContextPath()%>/FormularioInsertarVideojuego.do" class="btn btn-success">Insertar oferta nueva</a>
</div>
</body>
</html>
