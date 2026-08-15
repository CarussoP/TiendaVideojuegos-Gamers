<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Regla de decision - Proveedores que pueden surtir</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<h2>&iquest;Que proveedores pueden surtir un titulo?</h2>
	<p class="text-muted">Un proveedor califica si tiene cantidad suficiente Y esta autorizado (tabla proveedores_plataformas) para la plataforma de ese titulo.</p>

	<form action="<%=request.getContextPath()%>/BuscarProveedoresParaSurtir.do" method="GET" class="row row-cols-auto g-2 align-items-center mb-3">
		<div class="col">
			<select name="CATVID" class="form-select">
				<c:forEach var="c" items="${listaDeCatalogo}">
					<option value="${c.cat_vid_id}"
						<c:if test="${catVidIdConsultado eq c.cat_vid_id}">selected</c:if>>
						${c.cat_vid_nombre} (${c.plataforma.cat_pla_nombre})
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="col">
			<input type="number" name="CANT" class="form-control" placeholder="Cantidad requerida" value="${cantidadConsultada}" required>
		</div>
		<div class="col">
			<input type="submit" value="Buscar" class="btn btn-primary">
		</div>
	</form>

	<c:if test="${not empty proveedoresQuePuedenSurtir}">
		<table class="table table-striped">
			<tr>
				<th>Proveedor</th>
				<th>Precio de compra</th>
				<th>Precio de venta</th>
				<th>Cantidad disponible</th>
			</tr>
			<c:forEach var="oferta" items="${proveedoresQuePuedenSurtir}">
				<tr>
					<td>${oferta.proveedor.cat_prov_nombre}</td>
					<td>${oferta.vj_precio_compra}</td>
					<td>${oferta.vj_precio_venta}</td>
					<td>${oferta.vj_cantidad}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty proveedoresQuePuedenSurtir and not empty cantidadConsultada}">
		<p>Ningun proveedor puede surtir esa cantidad para ese titulo.</p>
	</c:if>
</div>
</body>
</html>
