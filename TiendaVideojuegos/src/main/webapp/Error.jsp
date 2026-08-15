<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Error</title>
</head>
<body>
<jsp:include page="/common/nav.jsp" />
<div class="container">
<h1>Ha ocurrido un error</h1>
<%
	String mensaje = (String) request.getAttribute("mensajeError");
	if(mensaje != null)
	{
%>
		<h2><%=mensaje%></h2>
<%
	}
	else if(exception != null)
	{
%>
		<h2><%=exception.getMessage()%></h2>
		<%
			if(exception.getCause() != null)
			{
		%>
				<h3>Causa: <%=exception.getCause().getMessage()%></h3>
		<%
			}
		%>
<%
	}
%>
<a href="<%=request.getContextPath()%>/MostrarVideojuegos.do" class="btn btn-primary">Volver al inicio</a>
</div>
</body>
</html>
