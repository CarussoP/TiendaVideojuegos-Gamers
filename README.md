# Tienda de Videojuegos "Gamers"

Aplicación web Java EE para gestionar el catálogo, proveedores, plataformas y ofertas de una tienda de videojuegos.

| Campo | Valor |
|-------|--------|
| **Versión** | `0.1.0-20260815T175153Z` |
| **Timestamp de respaldo** | `2026-08-15T17:51:53Z` |
| **Artifact** | `TiendaVideojuegos` (WAR) |
| **Lenguaje** | Java 16 |
| **Empaquetado** | Maven WAR |

## Estado actual (resumen)

- Arquitectura MVC con patrón **Command** (`Accion` + controladores por dominio)
- Persistencia con **Hibernate 5** sobre **MySQL** (`gamers`)
- Vistas **JSP** + Bootstrap 5.2
- **Modo oscuro** conmutable (slider en la barra superior; preferencia en `localStorage`)
- Branding "Gamers" con icono de control en el navbar

## Módulos

| Módulo | Extensión URL | Función |
|--------|---------------|---------|
| Ofertas / Videojuegos | `*.do` | CRUD de ofertas (precio compra/venta, stock por proveedor) |
| Regla de decisión | `BuscarProveedoresParaSurtir.do` | Buscar proveedores para surtir |
| Proveedores | `*.pro` | CRUD de catálogo de proveedores |
| Plataformas | `*.pla` | CRUD de plataformas/consolas |
| Asignaciones | `*.asig` | Relación proveedor–plataforma |

## Stack técnico

- Java Servlet API 4 / JSP / JSTL
- Hibernate 5.6 + MySQL Connector 8
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
        ├── java/          # Beans, Acciones, Controladores, Hibernate
        └── webapp/        # JSP, CSS/JS de tema, web.xml
```

## Requisitos para ejecutar

1. JDK 16+
2. Maven 3.x
3. Servidor de aplicaciones / contenedor servlet (p. ej. Tomcat) compatible con Servlet 4
4. MySQL con base de datos `gamers` (ver `hibernate.cfg.xml`)

### Configuración de BD

Archivo: `TiendaVideojuegos/src/main/java/hibernate.cfg.xml`

- URL por defecto: `jdbc:mysql://localhost/gamers?serverTimezone=UTC`
- Usuario por defecto: `root`

Ajusta usuario/contraseña según tu entorno local antes de desplegar.

### Build y despliegue

```bash
cd TiendaVideojuegos
mvn clean package
# Desplegar el WAR generado en target/ en tu contenedor servlet
```

## UI reciente

- Navbar compartido (`common/nav.jsp`) con enlaces a todos los módulos
- Toggle **Modo oscuro** (`css/theme.css`, `js/theme.js`)
- Fondo y tipografía adaptados a tablas y formularios en tema oscuro

## Documentación adicional

Ver [docs/ESTADO_ACTUAL.md](docs/ESTADO_ACTUAL.md) para el detalle del estado del proyecto en esta versión.
