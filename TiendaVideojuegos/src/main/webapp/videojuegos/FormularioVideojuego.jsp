<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Formulario de Oferta</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<c:choose>
		<c:when test="${not empty oferta}">
			<h2>Editar oferta #${oferta.vj_id}</h2>
			<form action="<%=request.getContextPath()%>/ModificarVideojuego.do" method="GET">
				<input type="hidden" name="VJ" value="${oferta.vj_id}">
		</c:when>
		<c:otherwise>
			<h2>Nueva oferta</h2>
			<form action="<%=request.getContextPath()%>/InsertarVideojuego.do" method="GET">
		</c:otherwise>
	</c:choose>

	<div class="row mb-2">
		<label for="CATVID">Titulo (catalogo)</label>
		<select name="CATVID" id="CATVID" class="form-select" required>
			<c:forEach var="c" items="${listaDeCatalogo}">
				<option value="${c.cat_vid_id}"
					<c:if test="${not empty oferta and oferta.videojuegoCatalogo.cat_vid_id eq c.cat_vid_id}">selected</c:if>>
					${c.cat_vid_nombre} (${c.plataforma.cat_pla_nombre})
				</option>
			</c:forEach>
		</select>
	</div>
	<div class="row mb-2">
		<label for="CATPROV">Proveedor</label>
		<select name="CATPROV" id="CATPROV" class="form-select" required>
			<c:forEach var="p" items="${listaDeProveedores}">
				<option value="${p.cat_prov_id}"
					<c:if test="${not empty oferta and oferta.proveedor.cat_prov_id eq p.cat_prov_id}">selected</c:if>>
					${p.cat_prov_nombre}
				</option>
			</c:forEach>
		</select>
	</div>
	<div class="row mb-2">
		<label for="PCOMPRA">Precio de compra</label>
		<input type="number" step="0.01" class="form-control" name="PCOMPRA" id="PCOMPRA" value="${oferta.vj_precio_compra}" required>
	</div>
	<div class="row mb-2">
		<label for="PVENTA">Precio de venta</label>
		<input type="number" step="0.01" class="form-control" name="PVENTA" id="PVENTA" value="${oferta.vj_precio_venta}" required>
	</div>
	<div class="row mb-2">
		<label for="CANT">Cantidad</label>
		<input type="number" class="form-control" name="CANT" id="CANT" value="${oferta.vj_cantidad}" required>
	</div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	</form>
</div>
</body>
</html>
