create database gestionacademica;
use gestionacademica;
create table carreras(
	id int primary key,
    nombre_carrera varchar(50),
    duracion tinyint,
    departamento varchar(50)
);
create table alumnos(
	id int primary key,
    nombre varchar(50),
    apellido varchar(50),
    edad tinyint,
    id_carrera int,
    foreign key (id_carrera) references carreras(id)
);
create table asignatura(
	id int primary key,
    nombre varchar(50),
    creditos int,
    id_carrera int,
    foreign key (id_carrera) references carreras(id)
);
use gestionacademica;
INSERT INTO carreras (id,nombre_carrera,duracion,departamento) VALUES (1,'CIENCIA DE DATOS', '5', 'SISTEMAS');
SELECT * FROM carreras;

use gestionacademica;
INSERT INTO carreras (id,nombre_carrera,duracion, departamento) VALUES (2,'ANALISIS DE DATOS', '3', 'SISTEMAS');
INSERT INTO carreras (id,nombre_carrera,duracion, departamento) VALUES (3,'DISEÑO WEB', '3', 'ARTES Y DISEÑO');
SELECT * FROM carreras;

INSERT INTO alumnos SET id = 101, nombre="Juan", apellido="Soto", edad = 25, id_carrera = 1;
INSERT INTO alumnos SET id = 102, nombre = "Gabriela", apellido = "Ruiz", edad = 35 , id_carrera = 2;
INSERT INTO alumnos SET id = 103, nombre = "Diego", apellido = "Lopez", edad = 29 , id_carrera = 3;
INSERT INTO alumnos SET id = 104, nombre = "Mariela", apellido = "Puertas", edad = 19 , id_carrera = 1;
SELECT * FROM alumnos;

update alumnos set nombre='Juan Jose' where id=101;

update asignaturas set nombre='BASE DE DATOS I', creditos=12 where id=3;


delete from asignaturas where id_carrera=1 and creditos=8;
select * from alumnos;
select * from carreras;
select * from asignaturas;


CREATE VIEW vista_alumnos_basica AS
SELECT vista_alumnos_basica id, nombre, apellido
FROM alumnos;

SELECT a.nombre, a.apellido, c.nombre_carrera
FROM alumnos a JOIN carreras c ON a.id_carrera = c.id;

CREATE VIEW vista_asignaturas_carrera AS
SELECT a.nombre AS nombre_asignatura, a.creditos, c.nombre_carrera
FROM asignaturas a JOIN carreras c ON a.id_carrera = c.id;


CREATE VIEW vista_asignaturas_credito_alto AS
SELECT nombre, creditos FROM asignaturas WHERE creditos > 5;

SELECT c.nombre_carrera, v.nombre AS nombre_asignatura
FROM carreras c JOIN asignaturas a ON c.id = a.id_carrera
JOIN vista_asignaturas_credito_alto v ON a.nombre = v.nombre
ORDER BY c.nombre_carrera;

CREATE VIEW vista_creditos_por_alumno AS
SELECT al.id, al.nombre, al.apellido,
(SELECT SUM(asi.creditos) 
     FROM asignaturas asi 
     WHERE asi.id_carrera = al.id_carrera) AS total_creditos_carrera
FROM alumnos al;
CREATE VIEW vista_alumnos_mayores AS
SELECT nombre, apellido, edad, id_carrera
FROM alumnos WHERE edad > 21;

CREATE VIEW vista_asignaturas_creditos AS
SELECT nombre AS nombre_asignatura, creditos, id_carrera
FROM asignaturas WHERE creditos > 3;

SELECT CONCAT(vam.nombre, ' ', vam.apellido) AS nombre_completo,
    vam.edad, c.nombre_carrera, vac.nombre_asignatura
FROM vista_alumnos_mayores vam
JOIN carreras c ON vam.id_carrera = c.id
JOIN vista_asignaturas_creditos vac ON vam.id_carrera = vac.id_carrera;

CREATE OR REPLACE VIEW vista_alumnos_basica AS
SELECT id, nombre, apellido, edad
FROM alumnos;


CREATE OR REPLACE VIEW vista_asignaturas_credito_alto AS
SELECT a.nombre, a.creditos, c.nombre_carrera  -- Columna añadida a través del JOIN
FROM asignaturas a JOIN carreras c ON a.id_carrera = c.id
WHERE a.creditos > 5;

CREATE OR REPLACE VIEW vista_asignaturas_carrera AS
SELECT a.nombre AS nombre_asignatura, a.creditos, c.nombre_carrera
FROM asignaturas a JOIN carreras c ON a.id_carrera = c.id
WHERE a.creditos > 3;

CREATE OR REPLACE VIEW vista_alumnos_mayores AS
SELECT a.nombre, a.apellido, a.edad, c.nombre_carrera
FROM alumnos a JOIN carreras c ON a.id_carrera = c.id
WHERE a.edad > 21;





CREATE OR REPLACE VIEW vista_creditos_por_alumno AS
SELECT al.id, al.nombre, al.apellido, c.nombre_carrera,
    (SELECT SUM(asi.creditos) 
     FROM asignaturas asi 
     WHERE asi.id_carrera = al.id_carrera) AS total_creditos_carrera
FROM alumnos al JOIN carreras c ON al.id_carrera = c.id;


SELECT nombre, apellido, edad
FROM alumnos WHERE edad < (SELECT AVG(edad) FROM alumnos);

SELECT c.id, c.nombre_carrera
FROM carreras c LEFT JOIN
    asignaturas a ON c.id = a.id_carrera
WHERE a.id_carrera IS NULL;

SELECT departamento, SUM(duracion) AS duracion_total
FROM carreras GROUP BY departamento;

SELECT c.nombre_carrera,
    COUNT(a.id) AS cantidad_alumnos
FROM carreras c JOIN alumnos a ON c.id = a.id_carrera
GROUP BY c.nombre_carrera;

SELECT departamento, COUNT(id) AS cantidad_carreras
FROM carreras GROUP BY departamento;

SELECT DISTINCT al.nombre, al.apellido
FROM alumnos al JOIN asignaturas asi ON al.id_carrera = asi.id_carrera
WHERE asi.creditos > 10;

SELECT al.nombre, al.apellido
FROM alumnos al JOIN carreras c ON al.id_carrera = c.id
WHERE c.departamento <> 'ARTES Y DISEÑO';


SELECT nombre
FROM asignaturas WHERE id_carrera IN (SELECT id_carrera 
	FROM alumnos WHERE edad = (SELECT MIN(edad) FROM alumnos));









