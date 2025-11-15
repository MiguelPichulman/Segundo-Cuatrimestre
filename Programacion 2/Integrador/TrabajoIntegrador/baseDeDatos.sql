create schema trabajofinal;
use trabajofinal;
CREATE TABLE trabajofinal.CredencialAcceso( 
id BIGINT PRIMARY KEY AUTO_INCREMENT,  
eliminado Boolean NOT NULL DEFAULT FALSE,  
hashPassword VARCHAR(255) NOT NULL, 
salt VARCHAR(64),  
ultimoCambio DATETIME NOT NULL DEFAULT 
CURRENT_TIMESTAMP,   
requiereReset Boolean NOT NULL DEFAULT FALSE
); 
CREATE TABLE trabajofinal.Usuario ( 
id BIGINT PRIMARY KEY AUTO_INCREMENT, 
eliminado BOOLEAN NOT NULL DEFAULT FALSE, 
username VARCHAR(30) NOT NULL UNIQUE, 
email VARCHAR(120) NOT NULL UNIQUE, 
activo BOOLEAN NOT NULL DEFAULT TRUE, 
fechaRegistro DATETIME NOT NULL DEFAULT 
CURRENT_TIMESTAMP, 
credencial_id BIGINT UNIQUE,  -- relación 1:1 
CONSTRAINT fk_credencial FOREIGN KEY (credencial_id) 
REFERENCES CredencialAcceso(id) 
ON DELETE CASCADE 
ON UPDATE CASCADE 
); 