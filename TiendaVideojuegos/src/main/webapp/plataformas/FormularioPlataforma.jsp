<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Formulario de Plataforma</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
	<c:choose>
		<c:when test="${not empty plataforma}">
			<h2>Editar plataforma #${plataforma.cat_pla_id}</h2>
			<form action="<%=request.getContextPath()%>/ModificarPlataforma.pla" method="GET">
				<input type="hidden" name="PLA" value="${plataforma.cat_pla_id}">
		</c:when>
		<c:otherwise>
			<h2>Nueva plataforma</h2>
			<form action="<%=request.getContextPath()%>/InsertarPlataforma.pla" method="GET">
		</c:otherwise>
	</c:choose>
	<div class="row mb-2">
		<label for="NOM">Nombre</label>
		<input type="text" class="form-control" name="NOM" id="NOM" value="${plataforma.cat_pla_nombre}" required>
	</div>
	<div class="row mb-2">
		<label for="COMP">Compania</label>
		<input type="text" class="form-control" name="COMP" id="COMP" value="${plataforma.cat_pla_compania}" required>
	</div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	</form>
</div>
</body>
</html>
