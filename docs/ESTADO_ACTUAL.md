# Estado actual del proyecto — Tienda Videojuegos "Gamers"

**Versión:** `0.1.0-20260815T175153Z`  
**Fecha/hora del respaldo (UTC):** `2026-08-15 17:51:53 UTC`  
**Branch:** `main` (respaldo inicial)  
**Formato de versión:** `MAJOR.MINOR.PATCH-YYYYMMDDTHHMMSSZ`

> Registro de **todas** las branches/checkpoints: [BRANCHES.md](BRANCHES.md).  
> Los snapshots posteriores viven en sus propias branches y **no** se fusionan a `main`.

---

## 1. Qué es este proyecto

Aplicación web de **back-office** para una tienda de videojuegos llamada **Gamers**. No es un videojuego: administra catálogos, proveedores, plataformas, asignaciones proveedor–plataforma y **ofertas** (quién vende un título, a qué precios y con qué stock).

Origen académico: curso **Java EE 2022** (`mx.com.cursodia.javaEE2022`), con refactorización posterior (módulos por dominio, OCP en resolución de acciones, catálogo separado de ofertas).

---

## 2. Versión de este respaldo

| Concepto | Valor |
|----------|--------|
| Versión semántica + timestamp | `0.1.0-20260815T175153Z` |
| Archivo de versión | [`VERSION`](../VERSION) en la raíz del repo |
| Significado | Primera versión documentada/respaldada en GitHub (`0.1.0`) en el instante UTC indicado |

---

## 3. Arquitectura

### Patrón general

```
Navegador (JSP + Bootstrap)
        │
        ▼
Controlador (Servlet por dominio)
        │  resuelve Accion por reflection (OCP)
        ▼
Accion concreta (Command)
        │
        ▼
Beans / Hibernate / MySQL
        │
        ▼
Forward a JSP de respuesta
```

### Dominios y controladores

| Dominio | Controlador | Mapping | Acciones típicas |
|---------|-------------|---------|------------------|
| Videojuegos / ofertas | `ControladorVideojuegos` | `*.do` | Mostrar, insertar, editar, borrar, filtrar, buscar proveedores |
| Proveedores | `ControladorProveedores` | `*.pro` | CRUD + formularios |
| Plataformas | `ControladorPlataformas` | `*.pla` | CRUD + formularios |
| Asignaciones | `ControladorAsignaciones` | `*.asig` | Mostrar, insertar, borrar |

La clase base `Accion` resuelve por reflection la clase `{Tipo}Accion` dentro del paquete del dominio, de modo que agregar una acción no exige tocar el controlador (Open/Closed).

### Modelo de datos (beans)

| Bean | Rol |
|------|-----|
| `VideojuegoCatalogo` | Título del catálogo (juego + plataforma) |
| `Videojuego` | Oferta de un proveedor sobre un título (precios, cantidad) |
| `Proveedor` | Proveedor |
| `Plataforma` | Plataforma / consola |
| `ProveedorPlataforma` | Asignación proveedor ↔ plataforma |

Mapeos XML Hibernate junto a cada bean; configuración en `hibernate.cfg.xml`.

---

## 4. Capa de presentación (estado UI)

### Vistas JSP

- `index.jsp` — home
- `videojuegos/` — listado, formulario, buscar proveedores
- `proveedores/` — listado y formulario
- `plataformas/` — listado y formulario
- `asignaciones/` — listado y formulario
- `Error.jsp` — página de error (incluye navbar)
- `common/nav.jsp` — navbar + Bootstrap + tema

### Tema claro / oscuro (implementado)

- Slider **Modo oscuro** en la esquina superior derecha del navbar
- Preferencia persistida en `localStorage` (`gamers-theme`)
- Estilos: `webapp/css/theme.css`
- Script: `webapp/js/theme.js`
- Aplica a todas las vistas que incluyen `nav.jsp`

### Branding

- Marca **Gamers** en el navbar
- Icono de control (CDN Flaticon) debajo del nombre

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

Compilación: **Java 16** (`maven-compiler-plugin` release 16). Empaquetado: **WAR**.

---

## 6. Qué incluye este respaldo en GitHub

- Código fuente bajo `TiendaVideojuegos/src`
- `pom.xml`
- Documentación (`README.md`, este archivo, `VERSION`)
- `.gitignore` (excluye `target/`, metadatos Eclipse, etc.)

**No** se versiona el directorio `target/` (artefactos de compilación).

---

## 7. Limitaciones / pendientes conocidos

- Credenciales de BD en `hibernate.cfg.xml` orientadas a entorno local (`root` / sin contraseña)
- Scripts SQL de esquema no incluidos en este snapshot (la app espera BD `gamers` ya creada)
- Sin autenticación de usuarios en la aplicación web
- Pruebas sueltas en paquete `pruebas` (Hibernate / Log4j), no suite de tests automatizados

---

## 8. Cómo reproducir el entorno

1. Crear BD MySQL `gamers` y tablas según el modelo Hibernate.
2. Ajustar `hibernate.cfg.xml`.
3. `mvn clean package` en `TiendaVideojuegos/`.
4. Desplegar el WAR en Tomcat (u otro contenedor Servlet 4).
5. Abrir la app y usar el menú; probar el toggle de modo oscuro.

---

*Documento generado para el respaldo GitHub de la versión `0.1.0-20260815T175153Z`.*
