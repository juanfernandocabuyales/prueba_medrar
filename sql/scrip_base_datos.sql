CREATE TABLE `empleado` (
  `empleado_id` int NOT NULL AUTO_INCREMENT,
  `empleado_nombre` varchar(1000) NOT NULL,
  PRIMARY KEY (`empleado_id`)
);

CREATE TABLE `actividad` (
  `actividad_id` int NOT NULL AUTO_INCREMENT,
  `actividad_nombre` varchar(1000) NOT NULL,
  `actividad_estado` varchar(100) NOT NULL,
  `actividad_fecha_inicio` datetime NOT NULL,
  `actividad_empleado` int NOT NULL,
  PRIMARY KEY (`actividad_id`),
  KEY `actividad_FK` (`actividad_empleado`),
  CONSTRAINT `actividad_FK` FOREIGN KEY (`actividad_empleado`) REFERENCES `empleado` (`empleado_id`)
);