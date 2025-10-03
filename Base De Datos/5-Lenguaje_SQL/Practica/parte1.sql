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
insert into carreras (id, nombre_carrera, duracion, departamento)
	values(1, 'CIENCIA DE DATOS', '5', 'SISTEMAS');
    select * from carreras;
use gestionacademica;
insert into carreras (id, nombre_carrera, duracion, departamento)
	values(2, 'ANALISIS DE DATOS', '3', 'SISTEMAS');
insert into carreras (id, nombre_carrera, duracion, departamento)
	values(3, 'DISENO WEB', '3', 'ARTES Y DISENO');
select * from carreras;

use gestionacademica;
insert into alumnos set id=101, nombre="Juan", apellido="Soto", edad=25, id_carrera=1;
select * from alumnos;



use gestionacademica;
insert into alumnos set id=102, nombre="Gabriela", apellido="Ruiz", edad= 35, id_carrera=2;
insert into alumnos set id=103, nombre="Diego", apellido="Lopez", edad=29, id_carrera=1;
insert into alumnos set id=104, nombre="Mariela", apellido="Puertas", edad=19, id_carrera=1;
select * from alumnos;

use gestionacademica;
update alumnos set nombre = "Juan Jose" where id=101;
select * from alumnos;

use gestionacademica;
update asignatura set nombre='BASES DE DATOS I', creditos=12 where id=3;
select * from asignatura;

use gestionacademica;
delete from alumnos where id=103;
select * from alumnos;

use gestionacademica;
delete from asignatura where id_carrera=1 and creditos=8;
select * from asignatura;

select nombre, apellido, edad from alumnos where edad>=25;

select nombre,creditos from asignatura where creditos!=8;

select nombre, apellido, edad from alumnos where nombre<> 'Diego';

select * from carreras where duracion<>5;

select * from carreras where duracion =5;

select * from asignatura where creditos=12;

select * from alumnos where id_carrera=1;

select * from asignatura where creditos>5;

select * from alumnos where edad <21;

select * from carreras where departamento ='SISTEMAS';

select * from asignatura where id_carrera=1 and creditos>=4;

select * from alumnos where apellido='Puertas';

SELECT * from carreras where duracion = 5 or departamento='ARTES Y DISENO'; 