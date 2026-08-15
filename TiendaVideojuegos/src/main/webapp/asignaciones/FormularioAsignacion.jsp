<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Nueva Asignacion Proveedor-Plataforma</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<h2>Autorizar proveedor para una plataforma</h2>
	<form action="<%=request.getContextPath()%>/InsertarAsignacion.asig" method="GET">
		<div class="row mb-2">
			<label for="PROV">Proveedor</label>
			<select name="PROV" id="PROV" class="form-select" required>
				<c:forEach var="p" items="${listaDeProveedores}">
					<option value="${p.cat_prov_id}">${p.cat_prov_nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="row mb-2">
			<label for="PLA">Plataforma</label>
			<select name="PLA" id="PLA" class="form-select" required>
				<c:forEach var="pl" items="${listaDePlataformas}">
					<option value="${pl.cat_pla_id}">${pl.cat_pla_nombre}</option>
				</c:forEach>
			</select>
		</div>
		<button type="submit" class="btn btn-primary">Guardar</button>
	</form>
</div>
</body>
</html>
