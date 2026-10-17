# Estado actual del proyecto — Tienda Videojuegos "Gamers"

**Versión:** `0.3.0-20261017T173923Z`  
**Última actualización (UTC):** `2026-10-17T17:39:23Z`  
**Branch de checkpoint:** `checkpoint/0.3.0-mvc-dao-isp-jpa`  
**Formato de versión:** `MAJOR.MINOR.PATCH-YYYYMMDDTHHMMSSZ`

> Esta branch **congela** el estado del proyecto tras completar la refactorización a **DAO + ISP + JPA**. Sirve como línea base documentada antes de la siguiente refactorización mayor.

---

## 1. Qué es este proyecto

Aplicación web de **back-office** para una tienda de videojuegos llamada **Gamers**. Administra catálogos, proveedores, plataformas, asignaciones proveedor–plataforma y **ofertas** (quién vende un título, a qué precios y con qué stock).

Origen académico: curso **Java EE 2022** (`mx.com.cursodia.javaEE2022`).

---

## 2. Historial de versiones / branches de checkpoint

| Versión | Branch | Qué congela |
|---------|--------|-------------|
| `0.1.0-20260815T175153Z` | `main` | Respaldo inicial (Hibernate XML, persistencia en beans) |
| `0.2.0-20260912T174933Z` | `refactor/isp-dao` | Checkpoint **antes** de ISP + DAO |
| `0.3.0-20261017T173923Z` | `checkpoint/0.3.0-mvc-dao-isp-jpa` | Checkpoint **después** de MVC + Command/OCP + DAO + ISP + JPA |

| Concepto | Valor |
|----------|--------|
| Versión anterior | `0.2.0-20260912T174933Z` |
| Versión actual | `0.3.0-20261017T173923Z` |
| Archivo de versión | [`VERSION`](../VERSION) |
| Significado | Congela arquitectura con patrones listados en §3 |

---

## 3. Patrones e implementaciones en esta versión

| Patrón / principio | Dónde vive | Rol |
|--------------------|------------|-----|
| **MVC** | `controlador/` + `acciones/` + JSP en `webapp/` | Separación presentación / control / datos |
| **Command** | `acciones.Accion` y `*Accion` por dominio | Cada URL resuelve una acción ejecutable |
| **OCP** (Open/Closed) | `Accion.resolver(...)` por reflection | Agregar acción = nueva clase; no tocar controladores |
| **DAO** | `dao.interfaces` + `dao.jpa` + `DaoFactory` | Persistencia fuera de las entidades |
| **ISP** (Interface Segregation) | `I*ConsultaDAO` / `I*ComandoDAO` | Clientes dependen solo de lectura o escritura |
| **JPA** | `@Entity` en Beans + `META-INF/persistence.xml` + `JpaHelper` | ORM estándar; Hibernate 5 como proveedor |
| **Factory** | `DaoFactory` | Punto único de obtención de DAOs |
| **DTO / Mapper** (API) | `api.ofertas.OfertaDtoMapper` | JSON sin exponer entidades crudas al cliente |

### Arquitectura vigente

```
Navegador (JSP + Bootstrap)  ──┐
                               │
API JSON (OfertasApiServlet) ──┤
                               ▼
Controlador (Servlet por dominio) / Servlet API
        │  Accion por reflection (Command + OCP)
        ▼
DaoFactory → interfaces ISP (Consulta / Comando)
        │
        ▼
DAO JPA (*DAOJpa) → EntityManager / JPQL
        │
        ▼
Entidades JPA (@Entity) + MySQL (BD `gamers`)
        │
        ▼
Hibernate 5 como proveedor (persistence.xml → gamersPU)
```

---

## 4. Estructura del repositorio (congelada)

```
VideojuegosRefactorized/
├── VERSION                          # 0.3.0-20261017T173923Z
├── README.md
├── docs/
│   └── ESTADO_ACTUAL.md             # este documento
└── TiendaVideojuegos/
    ├── pom.xml
    └── src/main/
        ├── java/mx/com/cursodia/javaEE2022/
        │   ├── Beans/               # Entidades JPA
        │   ├── DataBaseH/           # JpaHelper, DataBaseException
        │   ├── dao/
        │   │   ├── DaoFactory.java
        │   │   ├── interfaces/      # ISP: Consulta / Comando / DAO
        │   │   └── jpa/             # Implementaciones EntityManager
        │   ├── acciones/            # Command por dominio
        │   ├── controlador/         # Servlets MVC
        │   └── api/                 # REST JSON (Ofertas)
        ├── resources/META-INF/
        │   └── persistence.xml      # Unidad gamersPU
        └── webapp/                  # JSP, CSS/JS tema, web.xml
```

### Dominios y controladores

| Dominio | Controlador | Mapping | Acciones típicas |
|---------|-------------|---------|------------------|
| Videojuegos / ofertas | `ControladorVideojuegos` | `*.do` | Mostrar, insertar, editar, borrar, filtrar, buscar proveedores |
| Proveedores | `ControladorProveedores` | `*.pro` | CRUD + formularios |
| Plataformas | `ControladorPlataformas` | `*.pla` | CRUD + formularios |
| Asignaciones | `ControladorAsignaciones` | `*.asig` | Mostrar, insertar, borrar |
| API Ofertas | `OfertasApiServlet` | `/api/ofertas` | CRUD JSON |

### Modelo de datos (entidades JPA)

| Entidad | Tabla | Rol |
|---------|-------|-----|
| `VideojuegoCatalogo` | `cat_videojuegos` | Título del catálogo (juego + plataforma) |
| `Videojuego` | `videojuego` | Oferta de un proveedor (precios, cantidad) |
| `Proveedor` | `cat_proveedores` | Proveedor |
| `Plataforma` | `cat_plataformas` | Plataforma / consola |
| `ProveedorPlataforma` | `proveedores_plataformas` | Asignación proveedor ↔ plataforma |

### Capa DAO

| Paquete / clase | Rol |
|-----------------|-----|
| `dao.interfaces` | Contratos ISP (`I*ConsultaDAO`, `I*ComandoDAO`, `I*DAO`) |
| `dao.jpa` | Implementaciones JPA (`EntityManager` + JPQL) |
| `dao.DaoFactory` | Acceso a DAOs; expone la interfaz segregada |
| `DataBaseH.JpaHelper` | `EntityManagerFactory` de `gamersPU` |
| `META-INF/persistence.xml` | Configuración JPA (driver, URL, entidades, dialecto) |

---

## 5. Dependencias principales (`pom.xml`)

| Librería | Versión |
|----------|---------|
| mysql-connector-java | 8.0.31 |
| javax.persistence-api | 2.2 |
| hibernate-core (proveedor JPA) | 5.6.15.Final |
| log4j-core | 2.17.1 |
| jstl | 1.2 |
| javax.servlet-api | 4.0.1 (provided) |
| jaxb-api / jaxb-runtime | 2.3.x |
| gson | 2.11.0 |

Compilación: **Java 11**. Empaquetado: **WAR**.

---

## 6. Limitaciones / pendientes conocidos

- Credenciales de BD en `persistence.xml` orientadas a entorno local
- Scripts SQL de esquema no incluidos (la app espera BD `gamers` ya creada)
- Sin autenticación de usuarios en la aplicación web
- Pruebas sueltas en paquete `pruebas`, no suite automatizada
- API REST solo del módulo Ofertas (otros dominios siguen vía JSP)

---

## 7. Cómo reproducir el entorno

1. Crear BD MySQL `gamers` y tablas según el modelo de entidades JPA.
2. Ajustar `src/main/resources/META-INF/persistence.xml` (host/puerto/credenciales).
3. `mvn clean package` en `TiendaVideojuegos/`.
4. Desplegar el WAR en Tomcat (u otro contenedor Servlet 4).
5. Abrir la app (JSP) y/o consumir `/api/ofertas`.

---

## 8. Siguiente paso (fuera de este checkpoint)

Cualquier refactorización mayor posterior debe partir de una **nueva branch** desde este checkpoint, subir versión (`0.4.0-…`) y documentar el objetivo del cambio en `docs/ESTADO_ACTUAL.md`.

---

*Documento de la branch `checkpoint/0.3.0-mvc-dao-isp-jpa` — versión `0.3.0-20261017T173923Z`.*
