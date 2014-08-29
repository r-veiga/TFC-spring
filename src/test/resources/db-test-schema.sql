-- ==========================================================
-- SCRIPT DE PRUEBAS PARA LA BASE DE DATOS EN MEMORIA [ H2 ]
-- ==========================================================


-- Volcando estructura para tabla roberto.categoria

CREATE TABLE IF NOT EXISTS categoria (
  id varchar(50) NOT NULL,
  nombre varchar(50) NOT NULL,
  PRIMARY KEY (id)
);



-- Volcando estructura para tabla roberto.especialidad

CREATE TABLE IF NOT EXISTS especialidad (
  id int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(50) NOT NULL,
  PRIMARY KEY (id)
);



-- Volcando estructura para tabla roberto.ubicacion

CREATE TABLE IF NOT EXISTS ubicacion (
  id int(11) NOT NULL,
  nombre varchar(50) NOT NULL,
  PRIMARY KEY (id)
);



-- Volcando estructura para tabla roberto.paciente

CREATE TABLE IF NOT EXISTS paciente (
  id int(11) NOT NULL AUTO_INCREMENT,
  expediente varchar(20) NOT NULL DEFAULT '0',
  nombre varchar(50) NOT NULL DEFAULT '0',
  apellidos varchar(200) DEFAULT '0',
  dni varchar(10) DEFAULT '0',
  fecNacimiento date DEFAULT NULL,
  direccion varchar(250) DEFAULT '0',
  provincia varchar(100) DEFAULT '0',
  pais varchar(100) DEFAULT '0',
  codPostal varchar(100) DEFAULT '0',
  tlfFijo varchar(10) DEFAULT '0',
  tlfMovil varchar(10) DEFAULT '0',
  email varchar(250) DEFAULT '0',
  idUbicacion int(11) NULL DEFAULT NULL,
  fecIngreso timestamp NULL DEFAULT NULL,
  fecAlta timestamp NULL DEFAULT NULL,
  fecBaja timestamp NULL DEFAULT NULL,
  PRIMARY KEY (id),
  KEY expediente (expediente)
);



-- Volcando estructura para tabla roberto.cita

CREATE TABLE IF NOT EXISTS cita (
  fecha timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  idPaciente int(11) NOT NULL,
  idEspecialidad int(11) NOT NULL,
  PRIMARY KEY (fecha,idPaciente,idEspecialidad),
  KEY FK_CITA_PAC (idPaciente),
  KEY FK_CITA_ESP (idEspecialidad),
  CONSTRAINT FK_CITA_ESP FOREIGN KEY (idEspecialidad) REFERENCES especialidad (id),
  CONSTRAINT FK_CITA_PAC FOREIGN KEY (idPaciente) REFERENCES paciente (id)
);



-- Volcando estructura para tabla roberto.prueba

CREATE TABLE IF NOT EXISTS prueba (
  idPaciente int(11) NOT NULL,
  fecAlta timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  fecPrueba date NOT NULL,
  nombre varchar(50) DEFAULT NULL,
  descripcion varchar(500) DEFAULT NULL,
  archivo varchar(200) DEFAULT NULL,
  PRIMARY KEY (idPaciente,fecAlta),
  CONSTRAINT FK_PRB_PAC FOREIGN KEY (idPaciente) REFERENCES paciente (id)
);



-- Volcando estructura para tabla roberto.sanitario

CREATE TABLE IF NOT EXISTS sanitario (
  id int(11) NOT NULL AUTO_INCREMENT,
  colegiado varchar(50) NOT NULL,
  dni varchar(50) DEFAULT NULL,
  nombre varchar(50) NOT NULL,
  apellidos varchar(200) DEFAULT NULL,
  idEspecialidad int(11) NOT NULL,
  categoria varchar(50) NOT NULL,
  email varchar(250) DEFAULT NULL,
  tlfFijo varchar(10) DEFAULT NULL,
  tlfMovil varchar(10) DEFAULT NULL,
  fecNacimiento date NOT NULL,
  fecAlta timestamp NULL DEFAULT NULL,
  fecBaja timestamp NULL DEFAULT NULL,
  PRIMARY KEY (id),
  KEY colegiado (colegiado)
);



-- Volcando estructura para tabla roberto.usuario

CREATE TABLE IF NOT EXISTS nota (
  idSanitario int(11) NOT NULL,
  fecha timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  descripcion varchar(500) DEFAULT NULL,
  PRIMARY KEY (idSanitario,fecha)
);



-- Volcando estructura para tabla roberto.usuario

CREATE TABLE IF NOT EXISTS usuario (
  id int(11) NOT NULL AUTO_INCREMENT,
  user varchar(8) NOT NULL,
  pass varchar(32) NOT NULL,
  tipo varchar(50) DEFAULT NULL,
  nombre varchar(50) NOT NULL,
  apellidos varchar(200) DEFAULT NULL,
  email varchar(200) DEFAULT NULL,
  idSanitario int(11) DEFAULT NULL,
  fecAlta timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  fecBaja timestamp NULL DEFAULT NULL,
  fecAcceso timestamp NULL DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_USR_SAN (idSanitario),
  CONSTRAINT FK_USR_SAN FOREIGN KEY (idSanitario) REFERENCES sanitario (id)
);



-- Volcando estructura para tabla roberto.anotacion

CREATE TABLE IF NOT EXISTS anotacion (
  fecha timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  idSanitario int(11) NOT NULL,
  idPaciente int(11) NOT NULL,
  texto varchar(500) NOT NULL,
  PRIMARY KEY (fecha,idSanitario,idPaciente),
  KEY FK_NOTA_PAC (idPaciente),
  KEY FK_NOTA_SAN (idSanitario),
  CONSTRAINT FK_NOTA_PAC FOREIGN KEY (idPaciente) REFERENCES paciente (id),
  CONSTRAINT FK_NOTA_SAN FOREIGN KEY (idSanitario) REFERENCES sanitario (id)
);

