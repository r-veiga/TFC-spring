-- ==========================================================
--  DATOS DE PRUEBAS PARA LA BASE DE DATOS EN MEMORIA [ H2 ]
-- ==========================================================

-- Volcando datos para la tabla roberto.categoria: ~5 rows (aproximadamente)

INSERT INTO categoria (id, nombre) VALUES
	('AUXILIAR', 'Auxiliar'),
	('ENFERMERIA', 'Enfermero/a'),
	('MEDICO', 'Doctor/a'),
	('REHABILITACION', 'Rehabilitador'),
	('TRABAJO_SOCIAL', 'Trabajador Social');



-- Volcando datos para la tabla roberto.especialidad:

INSERT INTO especialidad (id, nombre) VALUES
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



-- Volcando datos para la tabla roberto.ubicacion: ~30 rows (aproximadamente)

INSERT INTO ubicacion (id, nombre) VALUES
	(10, 'URGENCIAS - BOX 10'),
	(11, 'URGENCIAS - BOX  1'),
	(12, 'URGENCIAS - BOX  2'),
	(13, 'URGENCIAS - BOX  3'),
	(14, 'URGENCIAS - BOX  4'),
	(15, 'URGENCIAS - BOX  5'),
	(16, 'URGENCIAS - BOX  6'),
	(17, 'URGENCIAS - BOX  7'),
	(18, 'URGENCIAS - BOX  8'),
	(19, 'URGENCIAS - BOX  9'),
	(101, 'CAMA - 101'),
	(102, 'CAMA - 102'),
	(103, 'CAMA - 103'),
	(104, 'CAMA - 104'),
	(105, 'CAMA - 105'),
	(106, 'CAMA - 106'),
	(107, 'CAMA - 107'),
	(108, 'CAMA - 108'),
	(109, 'CAMA - 109'),
	(110, 'CAMA - 110'),
	(201, 'CAMA - 201'),
	(202, 'CAMA - 202'),
	(203, 'CAMA - 203'),
	(204, 'CAMA - 204'),
	(205, 'CAMA - 205'),
	(206, 'CAMA - 206'),
	(207, 'CAMA - 207'),
	(208, 'CAMA - 208'),
	(209, 'CAMA - 209'),
	(210, 'CAMA - 210');



-- Volcando datos para la tabla roberto.paciente: ~5 rows (aproximadamente)

INSERT INTO paciente (id, expediente, nombre, apellidos, dni, fecNacimiento, direccion, provincia, pais, codPostal, tlfFijo, tlfMovil, email, idUbicacion, fecIngreso, fecAlta, fecBaja) VALUES
	(1, 'E0001', 'P1_Nombre', 'P1_Apellidos', '000001', '1900-01-01', 'P1_C/Calle', 'P1_Provincia', 'P1_Pais', 'CP001', '1001', '1002', 'p1@localhost', 101, '2014-01-01 01:00:00', '2014-01-01 01:00:00', NULL),
	(2, 'E0002', 'P2_Nombre', 'P2_Apellidos', '000002', '1900-02-02', 'P2_C/Calle', 'P2_Provincia', 'P2_Pais', 'CP002', '2002', '2002', 'p2@localhost', 202, '2014-02-02 02:00:00', '2014-02-02 02:00:00', NULL),
	(3, 'E0003', 'P3_Nombre', 'P3_Apellidos', '000003', '1900-03-03', 'P3_C/Calle', 'P3_Provincia', 'P3_Pais', 'CP003', '3003', '3003', 'p3@localhost', NULL, NULL, '2014-03-03 03:00:00', NULL),
	(4, 'E0004', 'P4_Nombre', 'P4_Apellidos', '000004', '1900-04-04', 'P4_C/Calle', 'P4_Provincia', 'P4_Pais', 'CP004', '4004', '4004', 'p4@localhost', NULL, NULL, '2014-04-04 04:00:00', NULL),
	(5, 'E0005', 'P5_Nombre', 'P5_Apellidos', '000005', '1900-05-05', 'P5_C/Calle', 'P5_Provincia', 'P5_Pais', 'CP005', '5005', '5005', 'p5@localhost', NULL, NULL, '2014-05-05 05:00:00', NULL);



-- Volcando datos para la tabla roberto.cita:

INSERT INTO cita (fecha, idPaciente, idEspecialidad) VALUES
	('2014-10-01 10:00:00', 1, 1),
	('2014-11-01 11:00:00', 1, 2),
	('2014-10-02 10:00:00', 2, 1),
	('2014-11-02 11:00:00', 2, 2),
	('2014-10-01 12:30:00', 3, 1),
	('2014-10-01 17:30:00', 4, 1);

-- Volcando datos para la tabla roberto.prueba:

INSERT INTO prueba (idPaciente, fecAlta, fecPrueba, nombre, descripcion, archivo) VALUES
	(1, '2014-01-01 01:00:00', '2014-01-01', 'Analitica', 'N1_Descripcion', 'N1_ruta'),
	(1, '2014-02-02 02:00:00', '2014-02-02', 'Rayos', 'N2_Descripcion', 'N2_ruta');



-- Volcando datos para la tabla roberto.sanitario: ~5 rows (aproximadamente)

INSERT INTO sanitario (id, colegiado, dni, nombre, apellidos, idEspecialidad, categoria, tlfFijo, tlfMovil, email, fecNacimiento, fecAlta, fecBaja) VALUES
	(1, 'C0001', NULL, 'M1', 'M1_Med', 1, 'MEDICO', NULL, NULL, 'm1@locahost', '1950-01-01', '2014-01-01 00:00:00', NULL),
	(2, 'C0002', NULL, 'M2', 'M2_Enf', 2, 'ENFERMERIA', NULL, NULL, 'm2@locahost', '1950-02-02', '2014-02-02 00:00:00', NULL),
	(3, 'C0003', NULL, 'M3', 'M3_Aux', 3, 'AUXILIAR', NULL, NULL, 'm3@locahost', '1950-03-03', '2014-03-03 00:00:00', NULL),
	(4, 'C0004', NULL, 'M4', 'M4_Rea', 4, 'REHABILITACION', NULL, NULL, 'm4@locahost', '1950-04-04', '2014-04-04 00:00:00', NULL),
	(5, 'C0005', NULL, 'M5', 'M5_TSoc', 5, 'TRABAJO_SOCIAL', NULL, NULL, 'm5@locahost', '1950-05-05', '2014-05-05 00:00:00', NULL);



-- Volcando datos para la tabla roberto.nota

INSERT INTO nota (idSanitario, fecha, descripcion) VALUES
	(1, '2014-01-01 01:00:00', 'Nota 1 M1'),
	(1, '2014-01-02 01:00:00', 'Nota 2 M1'),
	(1, '2014-01-03 01:00:00', 'Nota 3 M1'),
	(2, '2014-02-01 02:00:00', 'Nota 1 M2'),
	(2, '2014-02-02 02:00:00', 'Nota 2 M2');



-- Volcando datos para la tabla roberto.usuario

INSERT INTO usuario (id, user, pass, tipo, nombre, apellidos, email, idSanitario, fecAlta, fecBaja, fecAcceso) VALUES
	(0, 'admin', 'admin', 'ADMINISTRATIVO', 'u_Adm_n', 'u_Adm_a', 'admin@localhost', NULL, '2014-01-01 00:00:00', NULL, NULL),
	(1, 'med1',  'med1',  'SANITARIO',  'u_Med1_n',  'u_Med1_a',  'u_med@localhost',  1,  '2014-01-01 01:00:00', NULL, NULL),
	(2, 'enf1',  'enf1',  'SANITARIO',  'u_End1_n',  'u_End1_a',  'u_end@localhost',  2,  '2014-02-02 02:00:00', NULL, NULL),
	(3, 'aux1',  'aux1',  'SANITARIO',  'u_Aux1_n',  'u_Aux1_a',  'u_aux@localhost',  3,  '2014-03-03 03:00:00', NULL, NULL),
	(4, 'rea1',  'rea1',  'SANITARIO',  'u_Rea1_n',  'u_Rea1_a',  'u_rea@localhost',  4,  '2014-04-04 04:00:00', NULL, NULL),
	(5, 'tsoc1', 'tsoc1', 'SANITARIO',  'u_TSoc1_n', 'u_TSoc1_a', 'u_tsoc@localhost', 5,  '2014-05-05 05:00:00', NULL, NULL);



-- Volcando datos para la tabla roberto.anotacion:

INSERT INTO anotacion (fecha, idSanitario, idPaciente, texto) VALUES
	('2014-01-01 01:00:00', 1, 1, 'M1_P1_Nota1'),
	('2014-02-02 02:00:00', 2, 1, 'M2_P1_Nota1'),
	('2014-03-03 03:00:00', 3, 1, 'M3_P1_Nota1'),
	('2014-04-04 04:00:00', 4, 1, 'M4_P1_Nota1'),
	('2014-05-05 05:00:00', 5, 1, 'M5_P1_Nota1');

