# TP2 - Programación Orientada a Objetos en Java

Trabajo Práctico N°2 de la cátedra **Paradigmas de Programación** (UTN - FRM), Unidad 2: Organización, reutilización y recursos avanzados en POO.

Este proyecto escala el modelo desarrollado en el [TP1](https://github.com/juanigarciadev/PP-TP1-53555), incorporando manejo de packages, excepciones y persistencia por serialización (Ejercicio 1), interfaces (Ejercicio 2), genéricos acotados y wildcards (Ejercicio 3), y clases anidadas junto con hilos (Ejercicio 4).

## Estado actual

| Ejercicio | Descripción | Estado |
|---|---|---|
| 1 | Packages, excepción `CupoExcedidoException` y persistencia por serialización | 🔧 En progreso |
| 2 | Interfaz `Certificable`, nuevo tipo de actividad `Curso` y emisión de certificados | ⬜ Pendiente |
| 3 | Métodos genéricos acotados y wildcards (`filtrarActividadesPorTipo`, `calcularCostoMateriales`) | ⬜ Pendiente |
| 4 | Clase anidada `Inscripcion.TicketDeAcceso` e hilo `EnvioTicketsThread` | ⬜ Pendiente |

## Cómo ejecutar

Abrir el proyecto en IntelliJ IDEA y correr `App.java` (contiene el `main`). Se recomienda ejecutar desde una terminal real (no la consola integrada del IDE) para que el limpiado de pantalla se aproveche mejor durante la navegación por los menús.

Al iniciar, el sistema precarga datos de prueba (`Utilidades.poblarSistema`): estudiantes, salas y eventos ya creados con sus actividades e inscripciones, para poder probar los módulos sin cargar todo a mano.

## Menú principal

```
1. Gestión de eventos universitarios
2. Gestión de estudiantes
3. Inscripción a eventos
4. Gestión de salas
5. Salir del sistema
```

Cada módulo (excepto "Salir") tiene a su vez un submenú propio de **crear / mostrar / volver**, salvo el de inscripción, que guía al usuario paso a paso: elegir evento → elegir actividad → elegir estudiante por legajo.

## Estructura del proyecto

**Packages:**
- `modelo` — `EventoUniversitario`, `Sala`, `Estudiante`, `Inscripcion`.
- `modelo.actividades` — `Actividad` (abstracta), `Charla`, `Taller`. Próximamente `Curso` (Ejercicio 2).
- `excepciones` — `CupoExcedidoException`, excepción chequeada (extiende `Exception`) que representará el fallo al superar el cupo máximo de una actividad al inscribir (Ejercicio 1).
- Próximos packages a incorporar según el enunciado: `certificacion` (interfaz `Certificable`, Ejercicio 2) e `hilos` (`EnvioTicketsThread`, Ejercicio 4).
- El resto de las clases (`App`, `Utilidades`, y los módulos `Gestion*`) permanecen en el paquete por defecto, heredadas de la base del TP1.

**Modelo (heredado del TP1, ver su [README](https://github.com/juanigarciadev/PP-TP1-53555/blob/main/README.md) para el detalle completo):**
- `EventoUniversitario` — evento con ID, título, costo base, gratuidad, sala (agregación) y actividades (composición). Contador estático de instancias, constructor de copia, `calcularCostoEstimado()`.
- `Actividad` — clase abstracta con `Charla` y `Taller` como subclases concretas (herencia y polimorfismo).
- `Sala`, `Estudiante`, `Inscripcion` — igual que en TP1.

**Módulos de interacción (menús por consola):** `GestionEventos`, `GestionEstudiantes`, `GestionSalas`, `GestionInscripcion`, igual que en TP1.

## Próximos pasos

- **Ejercicio 1:** lanzar `CupoExcedidoException` desde `Actividad.inscribir(...)` cuando se supere el cupo máximo, atraparla en `App`, y agregar persistencia del evento por serialización (`persistirEvento()` / `recuperarEvento(id)`) con manejo granular de las excepciones de I/O.
- **Ejercicio 2:** interfaz `Certificable` (`ENTIDAD_EMISORA`, `generarCertificado(Estudiante)`), implementada por `Taller` y el nuevo tipo `Curso` (no por `Charla`).
- **Ejercicio 3:** `filtrarActividadesPorTipo(Class<T> tipo)` y `calcularCostoMateriales(List<? extends Actividad>)` en `EventoUniversitario`.
- **Ejercicio 4:** clase anidada `Inscripcion.TicketDeAcceso` (emitida solo si la inscripción está confirmada) y `EnvioTicketsThread` en el paquete `hilos`, ejecutando el envío de tickets en paralelo mientras el hilo principal sigue mostrando datos del evento.
