<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Asignaciones Proveedor-Plataforma</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<h2>Que plataformas maneja cada proveedor</h2>
	<table class="table table-striped">
		<tr>
			<th>ID</th>
			<th>Proveedor</th>
			<th>Plataforma</th>
			<th></th>
		</tr>
		<c:forEach var="a" items="${listaDeAsignaciones}">
			<tr>
				<td>${a.pp_id}</td>
				<td>${a.proveedor.cat_prov_nombre}</td>
				<td>${a.plataforma.cat_pla_nombre}</td>
				<td><input type="button" class="btn btn-sm btn-danger" value="Borrar" onclick="location.href='<%=request.getContextPath()%>/BorrarAsignacion.asig?PP=${a.pp_id}'"/></td>
			</tr>
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/FormularioInsertarAsignacion.asig" class="btn btn-success">Nueva asignacion</a>
</div>
</body>
</html>
