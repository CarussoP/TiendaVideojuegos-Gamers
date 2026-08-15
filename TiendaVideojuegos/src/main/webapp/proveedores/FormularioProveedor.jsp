<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Formulario de Proveedor</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<c:choose>
		<c:when test="${not empty proveedor}">
			<h2>Editar proveedor #${proveedor.cat_prov_id}</h2>
			<form action="<%=request.getContextPath()%>/ModificarProveedor.pro" method="GET">
				<input type="hidden" name="PROV" value="${proveedor.cat_prov_id}">
		</c:when>
		<c:otherwise>
			<h2>Nuevo proveedor</h2>
			<form action="<%=request.getContextPath()%>/InsertarProveedor.pro" method="GET">
		</c:otherwise>
	</c:choose>
	<div class="row mb-2">
		<label for="NOM">Nombre</label>
		<input type="text" class="form-control" name="NOM" id="NOM" value="${proveedor.cat_prov_nombre}" required>
	</div>
	<div class="row mb-2">
		<label for="TEL">Telefono</label>
		<input type="text" class="form-control" name="TEL" id="TEL" value="${proveedor.cat_prov_tel}">
	</div>
	<div class="row mb-2">
		<label for="MAIL">Correo</label>
		<input type="email" class="form-control" name="MAIL" id="MAIL" value="${proveedor.cat_prov_correo}">
	</div>
	<div class="row mb-2">
		<label for="DIR">Direccion</label>
		<input type="text" class="form-control" name="DIR" id="DIR" value="${proveedor.cat_prov_direccion}">
	</div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	</form>
</div>
</body>
</html>
