<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Plataformas</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<h2>Catalogo de Plataformas</h2>
	<table class="table table-striped">
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Compania</th>
			<th colspan="2"></th>
		</tr>
		<c:forEach var="pla" items="${listaDePlataformas}">
			<tr>
				<td>${pla.cat_pla_id}</td>
				<td>${pla.cat_pla_nombre}</td>
				<td>${pla.cat_pla_compania}</td>
				<td><input type="button" class="btn btn-sm btn-primary" value="Editar" onclick="location.href='<%=request.getContextPath()%>/FormularioEditarPlataforma.pla?PLA=${pla.cat_pla_id}'"/></td>
				<td><input type="button" class="btn btn-sm btn-danger" value="Borrar" onclick="location.href='<%=request.getContextPath()%>/BorrarPlataforma.pla?PLA=${pla.cat_pla_id}'"/></td>
			</tr>
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/FormularioInsertarPlataforma.pla" class="btn btn-success">Nueva plataforma</a>
</div>
</body>
</html>
