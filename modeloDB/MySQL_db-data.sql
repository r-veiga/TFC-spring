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
-- Volcando datos para la tabla tfc.anotacion: ~5 rows (aproximadamente)
DELETE FROM `anotacion`;
/*!40000 ALTER TABLE `anotacion` DISABLE KEYS */;
INSERT INTO `anotacion` (`fecha`, `idSanitario`, `idPaciente`, `texto`) VALUES
	('2014-01-01 01:00:00', 1, 1, 'M1_P1_Nota1'),
	('2014-02-02 02:00:00', 2, 1, 'M2_P1_Nota1'),
	('2014-03-03 03:00:00', 3, 1, 'M3_P1_Nota1'),
	('2014-04-04 04:00:00', 4, 1, 'M4_P1_Nota1'),
	('2014-05-05 05:00:00', 5, 1, 'M5_P1_Nota1');
/*!40000 ALTER TABLE `anotacion` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.categoria: ~5 rows (aproximadamente)
DELETE FROM `categoria`;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`id`, `nombre`) VALUES
	('AUXILIAR', 'Auxiliar'),
	('ENFERMERIA', 'Enfermero/a'),
	('MEDICO', 'Doctor/a'),
	('REHABILITACION', 'Rehabilitador'),
	('TRABAJO_SOCIAL', 'Trabajador Social');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.cita: ~4 rows (aproximadamente)
DELETE FROM `cita`;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` (`fecha`, `idPaciente`, `idEspecialidad`) VALUES
	('2014-10-01 10:00:00', 1, 1),
	('2014-11-01 12:00:00', 1, 2),
	('2014-10-02 11:00:00', 2, 1),
	('2014-11-02 12:00:00', 2, 2);
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.especialidad: ~11 rows (aproximadamente)
DELETE FROM `especialidad`;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
INSERT INTO `especialidad` (`id`, `nombre`) VALUES
	(1, 'Anestesista'),
	(2, 'Alergologia'),
	(3, 'Cardilogia'),
	(4, 'Dermatologia'),
	(5, 'Medicina general'),
	(6, 'Neumologia'),
	(7, 'Neurologia'),
	(8, 'Oftalmologia'),
	(9, 'Pediatria'),
	(10, 'Traumatologia'),
	(11, 'Unidad del dolor');
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.nota: ~5 rows (aproximadamente)
DELETE FROM `nota`;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` (`idSanitario`, `fecha`, `descripcion`) VALUES
	(1, '2014-01-01 01:00:00', 'Nota 1 M1'),
	(1, '2014-01-02 03:00:00', 'Nota 2 M1'),
	(1, '2014-01-03 05:00:00', 'Nota 3 M1'),
	(2, '2014-02-01 02:00:00', 'Nota 1 M2'),
	(2, '2014-02-02 04:00:00', 'Nota 2 M2');
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.paciente: ~5 rows (aproximadamente)
DELETE FROM `paciente`;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` (`id`, `expediente`, `nombre`, `apellidos`, `dni`, `fecNacimiento`, `direccion`, `provincia`, `pais`, `codPostal`, `tlfFijo`, `tlfMovil`, `email`, `fecIngreso`, `fecAlta`, `fecBaja`) VALUES
	(1, 'E0001', 'P1_Nombre', 'P1_Apellidos', '000001', '1900-01-01', 'P1_C/Calle', 'P1_Provincia', 'P1_Pais', 'CP001', '1001', '1002', 'p1@localhost', '2014-01-01 01:00:00', '2014-01-01 01:00:00', NULL),
	(2, 'E0002', 'P2_Nombre', 'P2_Apellidos', '000002', '1900-02-02', 'P2_C/Calle', 'P2_Provincia', 'P2_Pais', 'CP002', '2002', '2002', 'p2@localhost', '2014-02-02 02:00:00', '2014-02-02 02:00:00', NULL),
	(3, 'E0003', 'P3_Nombre', 'P3_Apellidos b', '000003', '1500-03-03', 'P3_C/Calle', 'P3_Provincia', 'P3_Pais', 'CP003', '3003', '3003', 'p3@localhost', NULL, '2014-03-03 03:00:00', NULL),
	(4, 'E0004', 'P4_Nombre', 'P4_Apellidos', '000004', '1900-04-04', 'P4_C/Calle', 'P4_Provincia', 'P4_Pais', 'CP004', '4004', '4004', 'p4@localhost', NULL, '2014-04-04 04:00:00', NULL),
	(5, 'E0005', 'P5_Nombre', 'P5_Apellidos', '000005', '1900-05-05', 'P5_C/Calle', 'P5_Provincia', 'P5_Pais', 'CP005', '5005', '5005', 'p5@localhost', NULL, '2014-05-05 05:00:00', NULL);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.prueba: ~2 rows (aproximadamente)
DELETE FROM `prueba`;
/*!40000 ALTER TABLE `prueba` DISABLE KEYS */;
INSERT INTO `prueba` (`idPaciente`, `fecAlta`, `fecPrueba`, `nombre`, `descripcion`, `archivo`) VALUES
	(1, '2014-01-01 01:00:00', '2014-01-01', 'Analitica', 'N1_Descripcion', 'N1_ruta'),
	(1, '2014-02-02 02:00:00', '2014-02-02', 'Rayos', 'N2_Descripcion', 'N2_ruta');
/*!40000 ALTER TABLE `prueba` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.sanitario: ~5 rows (aproximadamente)
DELETE FROM `sanitario`;
/*!40000 ALTER TABLE `sanitario` DISABLE KEYS */;
INSERT INTO `sanitario` (`id`, `colegiado`, `dni`, `nombre`, `apellidos`, `idEspecialidad`, `categoria`, `tlfFijo`, `tlfMovil`, `email`, `fecNacimiento`, `fecAlta`, `fecBaja`) VALUES
	(1, 'C0001', '', 'M1', 'M1_Med', 1, 'MEDICO', '', '', 'm1@locahost', '1950-01-01', '2014-01-01 00:00:00', NULL),
	(2, 'C0002', NULL, 'M2', 'M2_Enf', 2, 'ENFERMERIA', NULL, NULL, 'm2@locahost', '1950-02-02', '2014-02-02 00:00:00', NULL),
	(3, 'C0003', NULL, 'M3', 'M3_Aux', 3, 'AUXILIAR', NULL, NULL, 'm3@locahost', '1950-03-03', '2014-03-03 00:00:00', NULL),
	(4, 'C0004', NULL, 'M4', 'M4_Rea', 4, 'REHABILITACION', NULL, NULL, 'm4@locahost', '1950-04-04', '2014-04-04 00:00:00', NULL),
	(5, 'C0005', NULL, 'M5', 'M5_TSoc', 5, 'TRABAJO_SOCIAL', NULL, NULL, 'm5@locahost', '1950-05-05', '2014-05-05 00:00:00', NULL);
/*!40000 ALTER TABLE `sanitario` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.ubicacion: ~30 rows (aproximadamente)
DELETE FROM `ubicacion`;
/*!40000 ALTER TABLE `ubicacion` DISABLE KEYS */;
INSERT INTO `ubicacion` (`id`, `nombre`, `ocupada`) VALUES
	(10, 'URGENCIAS - BOX 10', NULL),
	(11, 'URGENCIAS - BOX  1', NULL),
	(12, 'URGENCIAS - BOX  2', NULL),
	(13, 'URGENCIAS - BOX  3', NULL),
	(14, 'URGENCIAS - BOX  4', NULL),
	(15, 'URGENCIAS - BOX  5', NULL),
	(16, 'URGENCIAS - BOX  6', NULL),
	(17, 'URGENCIAS - BOX  7', NULL),
	(18, 'URGENCIAS - BOX  8', NULL),
	(19, 'URGENCIAS - BOX  9', NULL),
	(101, 'CAMA - 101', 1),
	(102, 'CAMA - 102', NULL),
	(103, 'CAMA - 103', NULL),
	(104, 'CAMA - 104', NULL),
	(105, 'CAMA - 105', NULL),
	(106, 'CAMA - 106', NULL),
	(107, 'CAMA - 107', NULL),
	(108, 'CAMA - 108', NULL),
	(109, 'CAMA - 109', NULL),
	(110, 'CAMA - 110', NULL),
	(201, 'CAMA - 201', 2),
	(202, 'CAMA - 202', NULL),
	(203, 'CAMA - 203', NULL),
	(204, 'CAMA - 204', NULL),
	(205, 'CAMA - 205', NULL),
	(206, 'CAMA - 206', NULL),
	(207, 'CAMA - 207', NULL),
	(208, 'CAMA - 208', NULL),
	(209, 'CAMA - 209', NULL),
	(210, 'CAMA - 210', NULL);
/*!40000 ALTER TABLE `ubicacion` ENABLE KEYS */;

-- Volcando datos para la tabla tfc.usuario: ~6 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `user`, `pass`, `tipo`, `nombre`, `apellidos`, `email`, `idSanitario`, `fecAlta`, `fecBaja`, `fecAcceso`) VALUES
	(0, 'admin', 'admin', 'ADMINISTRATIVO', 'Admin', 'Admin', NULL, NULL, '2014-08-24 16:52:16', NULL, NULL),
	(1, 'med1', 'med1', 'SANITARIO', 'm1', NULL, NULL, 1, '2014-01-01 01:00:00', NULL, '2014-08-24 20:51:18'),
	(2, 'enf1', 'enf1', 'SANITARIO', 'm2', NULL, NULL, 2, '2014-02-02 02:00:00', NULL, '2014-08-24 18:32:15'),
	(3, 'aux1', 'aux1', 'SANITARIO', 'm3', NULL, NULL, 3, '2014-03-03 03:00:00', NULL, NULL),
	(4, 'rea1', 'rea1', 'SANITARIO', 'm4', NULL, NULL, 4, '2014-04-04 04:00:00', NULL, NULL),
	(5, 'tsoc1', 'tsoc1', 'SANITARIO', 'm5', NULL, NULL, 5, '2014-05-05 05:00:00', NULL, NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


