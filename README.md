# Tienda de Videojuegos "Gamers"

Aplicación web Java EE para gestionar el catálogo, proveedores, plataformas y ofertas de una tienda de videojuegos.

| Campo | Valor |
|-------|--------|
| **Versión** | `0.2.0-20260912T174933Z` |
| **Última actualización** | `2026-09-12T17:49:33Z` |
| **Branch** | `refactor/isp-dao` |
| **Artifact** | `TiendaVideojuegos` (WAR) |
| **Lenguaje** | Java 11 |
| **Empaquetado** | Maven WAR |

## Estado actual (resumen)

- Arquitectura MVC con patrón **Command** (`Accion` + controladores por dominio)
- Persistencia con **Hibernate 5** sobre **MySQL** (`gamers`); beans con acceso a datos estático
- Vistas **JSP** + Bootstrap 5.2 + **modo oscuro**
- API JSON inicial del módulo **Ofertas** (`Gson` + `OfertasApiServlet`)
- Branding "Gamers" con icono de control en el navbar

## Siguiente paso

En la branch `refactor/isp-dao`: implementar **ISP** y el patrón **DAO** (separar persistencia de los beans e interfaces segregadas por dominio).

## Módulos

| Módulo | Extensión / ruta | Función |
|--------|------------------|---------|
| Ofertas / Videojuegos | `*.do` | CRUD de ofertas (precio compra/venta, stock por proveedor) |
| API Ofertas | `/api/ofertas` | CRUD JSON del módulo ofertas |
| Regla de decisión | `BuscarProveedoresParaSurtir.do` | Buscar proveedores para surtir |
| Proveedores | `*.pro` | CRUD de catálogo de proveedores |
| Plataformas | `*.pla` | CRUD de plataformas/consolas |
| Asignaciones | `*.asig` | Relación proveedor–plataforma |

## Stack técnico

- Java Servlet API 4 / JSP / JSTL
- Hibernate 5.6 + MySQL Connector 8
- Gson 2.11
- Log4j2
- Bootstrap 5.2 (CDN)
- Maven (`packaging: war`)

## Estructura del repositorio

```
VideojuegosRefactorized/
├── VERSION
├── README.md
├── docs/
│   └── ESTADO_ACTUAL.md
└── TiendaVideojuegos/
    ├── pom.xml
    └── src/main/
        ├── java/          # Beans, Acciones, Controladores, API, Hibernate
        └── webapp/        # JSP, CSS/JS de tema, web.xml
```

## Requisitos para ejecutar

1. JDK 11+
2. Maven 3.x
3. Servidor de aplicaciones / contenedor servlet (p. ej. Tomcat) compatible con Servlet 4
4. MySQL con base de datos `gamers` (ver `hibernate.cfg.xml`)

### Configuración de BD

Archivo: `TiendaVideojuegos/src/main/java/hibernate.cfg.xml`

- URL por defecto (Docker local): `jdbc:mysql://localhost:3307/gamers?serverTimezone=UTC`
- Usuario por defecto: `root`

Ajusta host, puerto, usuario y contraseña según tu entorno antes de desplegar.

### Build y despliegue

```bash
cd TiendaVideojuegos
mvn clean package
# Desplegar el WAR generado en target/ en tu contenedor servlet
```

## Documentación adicional

Ver [docs/ESTADO_ACTUAL.md](docs/ESTADO_ACTUAL.md) para el detalle del estado, la versión, la branch y el plan ISP + DAO.
