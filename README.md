# Tienda de Videojuegos "Gamers"

Aplicación web Java EE para gestionar el catálogo, proveedores, plataformas y ofertas de una tienda de videojuegos.

| Campo | Valor |
|-------|--------|
| **Versión** | `0.3.0-20261017T173923Z` |
| **Última actualización** | `2026-10-17T17:39:23Z` |
| **Branch** | `checkpoint/0.3.0-mvc-dao-isp-jpa` |
| **Artifact** | `TiendaVideojuegos` (WAR) |
| **Lenguaje** | Java 11 |
| **Empaquetado** | Maven WAR |

## Checkpoint Git

Cada branch de checkpoint **congela** el estado del proyecto antes (o al cerrar) una refactorización mayor.

| Versión | Branch | Congela |
|---------|--------|---------|
| `0.1.0` | `main` | Respaldo inicial |
| `0.2.0` | `refactor/isp-dao` | Antes de ISP + DAO |
| `0.3.0` | `checkpoint/0.3.0-mvc-dao-isp-jpa` | MVC + Command/OCP + DAO + ISP + JPA |

## Patrones implementados (`0.3.0`)

- **MVC** — controladores + acciones + JSP
- **Command** + **OCP** — `Accion` resuelta por reflection por dominio
- **DAO** — persistencia en `dao.jpa`, entidades sin lógica de acceso a datos
- **ISP** — interfaces `*ConsultaDAO` / `*ComandoDAO`
- **JPA** — `@Entity` + `persistence.xml` (`gamersPU`); Hibernate 5 como proveedor
- **Factory** — `DaoFactory`
- **API JSON** — módulo Ofertas (`Gson` + DTO mapper)

## Estado actual (resumen)

- Entidades JPA anotadas; `JpaHelper` (`EntityManagerFactory`)
- UI JSP + Bootstrap 5.2 + modo oscuro + branding "Gamers"
- API REST del módulo Ofertas en `/api/ofertas`

## Módulos

| Módulo | Extensión / ruta | Función |
|--------|------------------|---------|
| Ofertas / Videojuegos | `*.do` | CRUD de ofertas |
| API Ofertas | `/api/ofertas` | CRUD JSON |
| Regla de decisión | `BuscarProveedoresParaSurtir.do` | Proveedores que pueden surtir |
| Proveedores | `*.pro` | CRUD proveedores |
| Plataformas | `*.pla` | CRUD plataformas |
| Asignaciones | `*.asig` | Relación proveedor–plataforma |

## Stack técnico

- Java Servlet API 4 / JSP / JSTL
- JPA 2.2 + Hibernate 5.6 (proveedor) + MySQL Connector 8
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
        ├── java/          # Beans (JPA), DAO, Acciones, Controladores, API
        ├── resources/     # META-INF/persistence.xml
        └── webapp/        # JSP, CSS/JS de tema, web.xml
```

## Requisitos para ejecutar

1. JDK 11+
2. Maven 3.x
3. Contenedor Servlet 4 (p. ej. Tomcat)
4. MySQL con BD `gamers`

### Configuración de BD

Archivo: `TiendaVideojuegos/src/main/resources/META-INF/persistence.xml` (unidad `gamersPU`)

- URL por defecto (Docker local): `jdbc:mysql://localhost:3307/gamers?serverTimezone=UTC`
- Usuario por defecto: `root`

### Build y despliegue

```bash
cd TiendaVideojuegos
mvn clean package
# Desplegar el WAR generado en target/ en tu contenedor servlet
```

## Documentación adicional

Ver [docs/ESTADO_ACTUAL.md](docs/ESTADO_ACTUAL.md) para arquitectura, patrones, estructura y historial de checkpoints.
