Historias de Usuario – Sistema de Gestión de Usuarios y Credenciales 
Programacion 2 TP integrador Rodriguez Joaquin, Pichulman Miguel, Monzon Martin, Olivero Christian.

Especificaciones funcionales completas del sistema CRUD de usuarios y credenciales con relación 1:1.

HU-001 – Crear Usuario con su Credencial

Como administrador del sistema
Quiero registrar un nuevo usuario junto con su credencial
Para que quede almacenado correctamente en la base de datos

Criterios de aceptación

Se debe ingresar username, email y contraseña (hash simulada).

El email y el username no pueden repetirse.

La credencial se crea primero, luego el usuario (transacción completa).

Si falla uno, se hace rollback.

HU-002 – Listar Todos los Usuarios Activos

Como administrador
Quiero ver un listado de usuarios no eliminados
Para consultar su información básica y su credencial asociada

Criterios de aceptación

Solo se muestran usuarios con eliminado = FALSE.

Se muestra username, email, estado y datos de la credencial.

Si no hay usuarios, mostrar mensaje correspondiente.

HU-003 – Buscar Usuario por ID

Como administrador
Quiero buscar un usuario por su ID
Para obtener rápidamente su información completa

Criterios de aceptación

Si el ID existe y no está eliminado, se muestra toda la información.

Si no existe, se muestra mensaje de no encontrado.

HU-004 – Buscar Usuario por Username

Como administrador
Quiero buscar un usuario por su nombre de usuario
Para verificar si está registrado y consultar sus datos

Criterios de aceptación

Si el username existe y el usuario no está eliminado, se muestran sus datos.

Si el username no existe, se muestra un mensaje indicando que no fue encontrado.

La comparación debe hacerse exactamente como fue ingresado.

HU-005 – Actualizar Datos del Usuario

Como administrador
Quiero actualizar los datos de un usuario
Para mantener su información actualizada

Criterios de aceptación

Se pueden modificar username, email y estado activo.

El username y el email nuevos no pueden estar repetidos.

Si el ID no existe, debe informarse.

Los datos deben validarse antes de actualizar.

HU-006 – Eliminar Usuario (Baja Lógica)

Como administrador
Quiero eliminar un usuario
Para que no aparezca más en el sistema

Criterios de aceptación

La eliminación es lógica (se marca eliminado = TRUE).

También debe eliminarse lógicamente la credencial asociada.

La operación debe realizarse dentro de una transacción.

Si alguno de los pasos falla, se hace rollback.

HU-007 – Actualizar Contraseña del Usuario

Como administrador
Quiero cambiar la contraseña de un usuario
Para mantener la seguridad del sistema

Criterios de aceptación

Se debe generar un nuevo hash y un nuevo salt.

Se debe actualizar la fecha del último cambio.

Si la credencial no existe o está eliminada, debe informarse.

HU-008 – Listar Todas las Credenciales Activas

Como administrador
Quiero ver todas las credenciales activas
Para controlar su estado general

Criterios de aceptación

Solo se muestran credenciales con eliminado = FALSE.

Se muestra hashPassword, salt, último cambio y si requiere reset.

HU-009 – Buscar Credencial por ID

Como administrador
Quiero consultar una credencial por su ID
Para revisar su información detallada

Criterios de aceptación

Si la credencial existe y no está eliminada, se muestra.

Si no existe, se informa que no fue encontrada.

HU-010 – Resetear Contraseña (Marcar requiereReset)

Como administrador
Quiero marcar una credencial para que requiera un cambio de contraseña
Para reforzar la seguridad del sistema

Criterios de aceptación

Se cambia el campo requiereReset a TRUE.

Debe existir la credencial.

Si la credencial no existe, se debe mostrar un mensaje.

HU-011 – Relación 1:1 entre Usuario y Credencial

Como sistema
Quiero mantener la relación 1 a 1 entre usuario y credencial
Para evitar inconsistencias en la base de datos

Criterios de aceptación

Un usuario solo puede tener una credencial.

Una credencial solo puede pertenecer a un usuario.

La clave foránea debe ser única.

Si se intenta asignar una credencial ya utilizada, se debe impedir.

Reglas de Negocio
Validación de Datos

RN-001: username, email y contraseña son obligatorios.

RN-002: username debe ser único.

RN-003: email debe ser único.

RN-004: Los espacios iniciales y finales se eliminan automáticamente (trim).

RN-005: El ID debe ser mayor que 0 para actualizar o eliminar.

Credenciales

RN-006: Una credencial solo puede pertenecer a un usuario.

RN-007: hashPassword y salt son obligatorios.

RN-008: El campo requiereReset puede ser TRUE o FALSE.

RN-009: ultimoCambio debe actualizarse al cambiar la contraseña.

Relación 1:1

RN-010: credencial_id en Usuario es UNIQUE.

RN-011: Una credencial no puede estar asignada a más de un usuario.

RN-012: Si se elimina un usuario, su credencial también debe marcarse eliminada.

Persistencia y Transacciones

RN-013: Crear usuario debe ser completamente transaccional.

RN-014: Eliminar usuario debe ser completamente transaccional.

RN-015: Si falla un paso de una operación compuesta, se debe realizar rollback.

Seguridad

RN-016: Todas las consultas deben realizarse con PreparedStatements.

RN-017: No se permite crear usuarios o credenciales duplicadas.

Soft Delete

RN-018: Ningún dato se elimina físicamente.

RN-019: Los listados solo muestran registros donde eliminado = FALSE.

Modelo de Datos
Usuario (1) ---- (1) CredencialAcceso

Usuario
- id (PK)
- username (UNIQUE)
- email (UNIQUE)
- activo (BOOLEAN)
- eliminado (BOOLEAN)
- credencial_id (FK UNIQUE)

CredencialAcceso
- id (PK)
- hashPassword
- salt
- ultimoCambio
- requiereReset
- eliminado (BOOLEAN)