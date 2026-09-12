# Estado actual del proyecto — Tienda Videojuegos "Gamers"

**Versión:** `0.2.0-20260912T174933Z`  
**Última actualización (UTC):** `2026-09-12T17:49:33Z`  
**Branch de trabajo:** `refactor/isp-dao`  
**Formato de versión:** `MAJOR.MINOR.PATCH-YYYYMMDDTHHMMSSZ`

---

## 1. Qué es este proyecto

Aplicación web de **back-office** para una tienda de videojuegos llamada **Gamers**. No es un videojuego: administra catálogos, proveedores, plataformas, asignaciones proveedor–plataforma y **ofertas** (quién vende un título, a qué precios y con qué stock).

Origen académico: curso **Java EE 2022** (`mx.com.cursodia.javaEE2022`), con refactorización posterior (módulos por dominio, OCP en resolución de acciones, catálogo separado de ofertas).

---

## 2. Versión de este checkpoint

| Concepto | Valor |
|----------|--------|
| Versión anterior | `0.1.0-20260815T175153Z` |
| Versión actual | `0.2.0-20260912T174933Z` |
| Archivo de versión | [`VERSION`](../VERSION) en la raíz del repo |
| Branch GitHub | `refactor/isp-dao` |
| Significado | Checkpoint previo a la refactorización mayor **ISP + patrón DAO** |

---

## 3. Estado actual (qué hay hoy)

### Arquitectura vigente

```
Navegador (JSP + Bootstrap)  ──┐
                               │
API JSON (OfertasApiServlet) ──┤
                               ▼
Controlador (Servlet por dominio) / Servlet API
        │  Accion por reflection (OCP)
        ▼
Beans con lógica de persistencia estática (Hibernate)
        │
        ▼
MySQL (BD `gamers`)
```

### Lo implementado hasta `0.2.0`

- MVC + patrón **Command** (`Accion` + controladores por dominio)
- Persistencia **Hibernate 5** con mapeos XML; consultas con `JOIN FETCH` / `lazy="false"` para evitar LazyInitializationException en JSP
- UI JSP + Bootstrap 5.2 + tema claro/oscuro
- Dependencia **Gson**; capa inicial **API REST JSON** del módulo Ofertas (`api/ofertas`)
- Compilación objetivo **Java 11**; recursos XML de Hibernate empaquetados desde `src/main/java`
- BD local configurable (p. ej. Docker en puerto `3307` mientras XAMPP ocupa `3306`)

### Dominios y controladores

| Dominio | Controlador | Mapping | Acciones típicas |
|---------|-------------|---------|------------------|
| Videojuegos / ofertas | `ControladorVideojuegos` | `*.do` | Mostrar, insertar, editar, borrar, filtrar, buscar proveedores |
| Proveedores | `ControladorProveedores` | `*.pro` | CRUD + formularios |
| Plataformas | `ControladorPlataformas` | `*.pla` | CRUD + formularios |
| Asignaciones | `ControladorAsignaciones` | `*.asig` | Mostrar, insertar, borrar |
| API Ofertas | `OfertasApiServlet` | `/api/ofertas` | CRUD JSON |

### Modelo de datos (beans)

| Bean | Rol |
|------|-----|
| `VideojuegoCatalogo` | Título del catálogo (juego + plataforma) |
| `Videojuego` | Oferta de un proveedor sobre un título (precios, cantidad) |
| `Proveedor` | Proveedor |
| `Plataforma` | Plataforma / consola |
| `ProveedorPlataforma` | Asignación proveedor ↔ plataforma |

**Problema estructural actual:** los beans concentran entidad + acceso a datos (métodos estáticos Hibernate). Eso acopla el dominio a la persistencia y dificulta ISP.

---

## 4. Siguiente paso (esta branch)

Refactorización mayor orientada a:

1. **ISP (Interface Segregation Principle)** — interfaces de acceso a datos segregadas por responsabilidad/dominio, sin forzar a los clientes a depender de métodos que no usan.
2. **Patrón DAO** — extraer la persistencia de los beans hacia DAOs (p. ej. Hibernate) detrás de esas interfaces; los beans quedan como modelo de dominio.

Alcance previsto: capas `dao` / interfaces, adaptación de `Accion`, controladores y API Ofertas para consumir DAOs en lugar de métodos estáticos en beans.

---

## 5. Dependencias principales (`pom.xml`)

| Librería | Versión |
|----------|---------|
| mysql-connector-java | 8.0.31 |
| hibernate-core | 5.6.15.Final |
| log4j-core | 2.17.1 |
| jstl | 1.2 |
| javax.servlet-api | 4.0.1 (provided) |
| jaxb-api / jaxb-runtime | 2.3.x |
| gson | 2.11.0 |

Compilación: **Java 11**. Empaquetado: **WAR**.

---

## 6. Limitaciones / pendientes conocidos

- Credenciales de BD en `hibernate.cfg.xml` orientadas a entorno local
- Scripts SQL de esquema no incluidos (la app espera BD `gamers` ya creada)
- Sin autenticación de usuarios en la aplicación web
- Persistencia aún embebida en beans (se aborda en el siguiente paso ISP + DAO)
- Pruebas sueltas en paquete `pruebas`, no suite automatizada

---

## 7. Cómo reproducir el entorno

1. Crear BD MySQL `gamers` y tablas según el modelo Hibernate.
2. Ajustar `hibernate.cfg.xml` (host/puerto/credenciales).
3. `mvn clean package` en `TiendaVideojuegos/`.
4. Desplegar el WAR en Tomcat (u otro contenedor Servlet 4).
5. Abrir la app (JSP) y/o consumir `/api/ofertas`.

---

*Documento actualizado para el checkpoint GitHub `0.2.0-20260912T174933Z` en la branch `refactor/isp-dao`.*
