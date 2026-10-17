# Registro general de branches (checkpoints)

Documento canónico en **`main`**: inventario de snapshots.  
**No** se hace merge de branches de checkpoint a `main`; solo se actualiza esta tabla (y el resumen del `README`) con commits de documentación.

Política: ver regla Cursor `branch-checkpoint-policy`.

| Versión | Branch | Fecha (UTC) | Qué congela / características |
|---------|--------|-------------|-------------------------------|
| `0.1.0-20260815T175153Z` | `main` (respaldo inicial) | `2026-08-15T17:51:53Z` | Backup inicial: MVC + Command/OCP; Hibernate XML; persistencia en beans |
| `0.2.0-20260912T174933Z` | `refactor/isp-dao` | `2026-09-12T17:49:33Z` | Checkpoint **antes** de ISP + DAO; API Ofertas; JOIN FETCH |
| `0.3.0-20261017T173923Z` | `checkpoint/0.3.0-mvc-dao-isp-jpa` | `2026-10-17T17:39:23Z` | MVC + Command/OCP + **DAO** + **ISP** + **JPA** (EntityManager, `persistence.xml`) |

## Cómo agregar una fila

1. Comparar la branch de la **versión anterior** con el estado actual.
2. Crear la nueva branch checkpoint (sin PR / sin merge a `main`).
3. En `main`, añadir una fila a esta tabla con versión, branch, fecha y puntos clave del diff.
