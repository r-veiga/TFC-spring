-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.14 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para tfc
DROP DATABASE IF EXISTS `tfc`;
CREATE DATABASE IF NOT EXISTS `tfc` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci */;
USE `tfc`;


-- Volcando estructura para tabla tfc.anotacion
DROP TABLE IF EXISTS `anotacion`;
CREATE TABLE IF NOT EXISTS `anotacion` (
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idSanitario` int(11) NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `texto` varchar(500) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`fecha`,`idSanitario`,`idPaciente`),
  KEY `FK_NOTA_PAC` (`idPaciente`),
  KEY `FK_NOTA_SAN` (`idSanitario`),
  CONSTRAINT `FK_NOTA_PAC` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`id`),
  CONSTRAINT `FK_NOTA_SAN` FOREIGN KEY (`idSanitario`) REFERENCES `sanitario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.categoria
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `nombre` varchar(50) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.cita
DROP TABLE IF EXISTS `cita`;
CREATE TABLE IF NOT EXISTS `cita` (
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idPaciente` int(11) NOT NULL,
  `idEspecialidad` int(11) NOT NULL,
  PRIMARY KEY (`fecha`,`idPaciente`,`idEspecialidad`),
  KEY `FK_CITA_PAC` (`idPaciente`),
  KEY `FK_CITA_ESP` (`idEspecialidad`),
  CONSTRAINT `FK_CITA_ESP` FOREIGN KEY (`idEspecialidad`) REFERENCES `especialidad` (`id`),
  CONSTRAINT `FK_CITA_PAC` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.especialidad
DROP TABLE IF EXISTS `especialidad`;
CREATE TABLE IF NOT EXISTS `especialidad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.nota
DROP TABLE IF EXISTS `nota`;
CREATE TABLE IF NOT EXISTS `nota` (
  `idSanitario` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `descripcion` varchar(500) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`idSanitario`,`fecha`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.paciente
DROP TABLE IF EXISTS `paciente`;
CREATE TABLE IF NOT EXISTS `paciente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expediente` varchar(20) COLLATE latin1_general_ci NOT NULL DEFAULT '0',
  `nombre` varchar(50) COLLATE latin1_general_ci NOT NULL DEFAULT '0',
  `apellidos` varchar(200) COLLATE latin1_general_ci DEFAULT '0',
  `dni` varchar(10) COLLATE latin1_general_ci DEFAULT '0',
  `fecNacimiento` date DEFAULT NULL,
  `direccion` varchar(250) COLLATE latin1_general_ci DEFAULT '0',
  `provincia` varchar(100) COLLATE latin1_general_ci DEFAULT '0',
  `pais` varchar(100) COLLATE latin1_general_ci DEFAULT '0',
  `codPostal` varchar(100) COLLATE latin1_general_ci DEFAULT '0',
  `tlfFijo` varchar(10) COLLATE latin1_general_ci DEFAULT '0',
  `tlfMovil` varchar(10) COLLATE latin1_general_ci DEFAULT '0',
  `email` varchar(250) COLLATE latin1_general_ci DEFAULT '0',
  `fecIngreso` timestamp NULL DEFAULT NULL,
  `fecAlta` timestamp NULL DEFAULT NULL,
  `fecBaja` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `expediente` (`expediente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.prueba
DROP TABLE IF EXISTS `prueba`;
CREATE TABLE IF NOT EXISTS `prueba` (
  `idPaciente` int(11) NOT NULL,
  `fecAlta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecPrueba` date NOT NULL,
  `nombre` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `descripcion` varchar(500) COLLATE latin1_general_ci DEFAULT NULL,
  `archivo` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`idPaciente`,`fecAlta`),
  CONSTRAINT `FK_PRB_PAC` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.sanitario
DROP TABLE IF EXISTS `sanitario`;
CREATE TABLE IF NOT EXISTS `sanitario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `colegiado` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `dni` varchar(10) COLLATE latin1_general_ci DEFAULT NULL,
  `nombre` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `apellidos` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `idEspecialidad` int(11) NOT NULL,
  `categoria` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `tlfFijo` varchar(10) COLLATE latin1_general_ci DEFAULT NULL,
  `tlfMovil` varchar(10) COLLATE latin1_general_ci DEFAULT NULL,
  `email` varchar(250) COLLATE latin1_general_ci DEFAULT NULL,
  `fecNacimiento` date NOT NULL,
  `fecAlta` timestamp NULL DEFAULT NULL,
  `fecBaja` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `colegiado` (`colegiado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.ubicacion
DROP TABLE IF EXISTS `ubicacion`;
CREATE TABLE IF NOT EXISTS `ubicacion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `ocupada` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla tfc.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(8) COLLATE latin1_general_ci NOT NULL,
  `pass` varchar(32) COLLATE latin1_general_ci NOT NULL,
  `tipo` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `nombre` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `apellidos` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `email` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `idSanitario` int(11) DEFAULT NULL,
  `fecAlta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecBaja` timestamp NULL DEFAULT NULL,
  `fecAcceso` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USR_SAN` (`idSanitario`),
  CONSTRAINT `FK_USR_SAN` FOREIGN KEY (`idSanitario`) REFERENCES `sanitario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- La exportación de datos fue deseleccionada.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

