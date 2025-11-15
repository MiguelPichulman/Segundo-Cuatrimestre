Sistema de Gestión de Usuarios y Credenciales de Acceso
Trabajo Práctico Integrador – Programación 2 - Comision 4
Miguel Pichulman, Rodriguez Joaquim, Christian Olivero, Martin Monzon 
Descripción del Proyecto

Este Trabajo Práctico Integrador consiste en el desarrollo de un sistema de gestión de usuarios y credenciales de acceso, aplicando Programación Orientada a Objetos, arquitectura en capas y persistencia con JDBC y MySQL.

El sistema permite realizar operaciones CRUD completas sobre las entidades Usuario y CredencialAcceso, manteniendo integridad, validaciones, transacciones y eliminación lógica.

Objetivos del Proyecto

El desarrollo de este sistema permite aplicar los principales contenidos de la materia:

Arquitectura en Capas

Capa Main (interfaz de consola).

Capa Service (reglas de negocio, validaciones, transacciones).

Capa DAO (acceso a datos con JDBC).

Capa Model (entidades del dominio).

Programación Orientada a Objetos

Uso de herencia mediante la clase abstracta Base.

Encapsulamiento con getters y setters.

Interfaces genéricas: GenericDAO y GenericService.

Asociación 1→1 entre Usuario y CredencialAcceso.

Persistencia con JDBC

Conexión mediante clase DatabaseConnection.

PreparedStatements en todas las consultas.

Recuperación de IDs autogenerados.

JOIN entre Usuario y CredencialAcceso.

Manejo de transacciones con commit y rollback.

Manejo de Recursos y Excepciones

Try-with-resources en consultas de lectura.

Uso de rollback para mantener atomicidad.

Propagación controlada de excepciones.

Validaciones e Integridad

Validación de email, username y contraseña.

Unicidad de email y username (BD + aplicación).

Eliminación lógica en ambas entidades.

Relación 1→1 con integridad referencial.

Funcionalidades Implementadas
Gestión de Usuario

Crear usuario con su credencial asociada.

Listar todos los usuarios no eliminados.

Actualizar datos personales.

Eliminar usuario (baja lógica) junto con su credencial.

Buscar usuario por ID.

Buscar usuario por username.

Gestión de Credencial de Acceso

Actualizar contraseña (hash y salt simulados).

Baja lógica automática al eliminar el usuario.

Requisitos del Sistema
Componente	Versión
Java JDK	17
MySQL	8.x
IDE	NetBeans 27
JDBC Driver	mysql-connector-j 8.4.x
Instalación
1. Crear Base de Datos
CREATE DATABASE trabajofinal;
USE trabajofinal;

CREATE TABLE CredencialAcceso( 
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    hashPassword VARCHAR(255) NOT NULL,
    salt VARCHAR(64) NOT NULL,
    ultimoCambio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    requiereReset BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    username VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fechaRegistro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    credencial_id BIGINT UNIQUE,
    CONSTRAINT fk_credencial FOREIGN KEY (credencial_id)
        REFERENCES CredencialAcceso(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

Ejecución

Abrir el proyecto en NetBeans.

Compilar y ejecutar la clase:

main.Main


Se abrirá el menú de consola.

Menú Principal
========= GESTION DE USUARIOS (TFI) =========
--- CRUD Usuario ---
1. Crear Usuario (Alta Usuario y Credencial)
2. Listar Usuarios
3. Actualizar Usuario
4. Eliminar Usuario
--- Busquedas ---
5. Buscar Usuario por ID
6. Buscar Usuario por Username
--- CRUD Credencial ---
7. Actualizar Contraseña
0. Salir

Arquitectura del Sistema
Estructura en Capas
Main (consola)
 AppMenu
MenuHandler
MenuDisplay
Service
 UsuarioService
 CredencialAccesoService
DAO
 UsuarioDAO
 CredencialAccesoDAO
Model
Base
Usuario
redencialAcceso
Config
DatabaseConnection

Modelo de Datos (ER)
┌──────────────────────┐        ┌────────────────────────┐
│       Usuario         │1 ---- 1│    CredencialAcceso    │
├──────────────────────┤        ├────────────────────────┤
│ id (PK)              │        │ id (PK)                │
│ username UNIQUE      │        │ hashPassword           │
│ email UNIQUE         │        │ salt                   │
│ activo               │        │ requiereReset          │
│ eliminado            │        │ eliminado              │
│ credencial_id (FK)   │        │ ultimoCambio           │
└──────────────────────┘        └────────────────────────┘

UML Simplificado de Entidades
          ┌───────────────┐
          │     Base       │
          ├───────────────┤
          │ - id: long     │
          │ - eliminado    │
          └───────▲───────┘
                  │
 ┌────────────────┴──────────────┐
 │                                 │
 │                                 │
┌──────────────┐         ┌──────────────────┐
│   Usuario     │         │ CredencialAcceso │
├──────────────┤         ├──────────────────┤
│ username     │         │ hashPassword     │
│ email        │         │ salt             │
│ activo       │         │ ultimoCambio     │
│ fechaRegistro│         │ requiereReset    │
│ credencialId │ 1 → 1   │                  │
└──────────────┘         └──────────────────┘

Reglas de Negocio (Resumen)

El username y el email deben ser únicos.

Un Usuario debe tener exactamente una Credencial asociada.

La eliminación debe ser lógica en ambas entidades.

Si al crear usuario falla la creación de credencial, debe hacerse rollback.

El sistema debe validar email con expresión regular.

Buenas Prácticas Aplicadas

Arquitectura en capas.

Patrón DAO y Service.

Uso de interfaces genéricas.

PreparedStatements (100%).

Eliminación lógica (soft delete).

Validaciones antes de persistir.

JOIN para obtener usuario con credencial.

Transacciones atómicas en operaciones compuestas.

Limitaciones del Sistema

No permite múltiples credenciales por usuario.

No tiene interfaz gráfica (solo consola).

El hash y salt son simulados.

No usa pool de conexiones.

Tecnologías Utilizadas

Java 17

MySQL 8.x

NetBeans 27

JDBC Driver MySQL

UMLetino para diagramas UML