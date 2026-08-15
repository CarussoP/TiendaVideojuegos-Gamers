<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Proveedores</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<h2>Catalogo de Proveedores</h2>
	<table class="table table-striped">
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Telefono</th>
			<th>Correo</th>
			<th>Direccion</th>
			<th colspan="2"></th>
		</tr>
		<c:forEach var="prov" items="${listaDeProveedores}">
			<tr>
				<td>${prov.cat_prov_id}</td>
				<td>${prov.cat_prov_nombre}</td>
				<td>${prov.cat_prov_tel}</td>
				<td>${prov.cat_prov_correo}</td>
				<td>${prov.cat_prov_direccion}</td>
				<td><input type="button" class="btn btn-sm btn-primary" value="Editar" onclick="location.href='<%=request.getContextPath()%>/FormularioEditarProveedor.pro?PROV=${prov.cat_prov_id}'"/></td>
				<td><input type="button" class="btn btn-sm btn-danger" value="Borrar" onclick="location.href='<%=request.getContextPath()%>/BorrarProveedor.pro?PROV=${prov.cat_prov_id}'"/></td>
			</tr>
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/FormularioInsertarProveedor.pro" class="btn btn-success">Nuevo proveedor</a>
</div>
</body>
</html>
