<script>
(function () {
	try {
		if (localStorage.getItem("gamers-theme") === "dark") {
			document.documentElement.classList.add("dark-mode");
		}
	} catch (e) { /* ignore */ }
})();
</script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link href="<%=request.getContextPath()%>/css/theme.css" rel="stylesheet">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
	<div class="container-fluid">
		<span class="navbar-brand d-flex flex-column align-items-center lh-1">
			Gamers
			<img
				src="https://cdn-icons-png.flaticon.com/512/808/808439.png"
				alt="Control de videojuegos"
				width="36"
				height="36"
				class="mt-1"
			/>
		</span>
		<div class="navbar-nav">
			<a class="nav-link" href="<%=request.getContextPath()%>/MostrarVideojuegos.do">Ofertas</a>
			<a class="nav-link" href="<%=request.getContextPath()%>/BuscarProveedoresParaSurtir.do">Regla de decision</a>
			<a class="nav-link" href="<%=request.getContextPath()%>/MostrarProveedores.pro">Proveedores</a>
			<a class="nav-link" href="<%=request.getContextPath()%>/MostrarPlataformas.pla">Plataformas</a>
			<a class="nav-link" href="<%=request.getContextPath()%>/MostrarAsignaciones.asig">Asignaciones</a>
		</div>
		<div class="form-check form-switch theme-switch mb-0">
			<input class="form-check-input" type="checkbox" role="switch" id="themeToggle" aria-label="Activar modo oscuro">
			<label class="form-check-label" for="themeToggle">Modo oscuro</label>
		</div>
	</div>
</nav>
<script src="<%=request.getContextPath()%>/js/theme.js"></script>
