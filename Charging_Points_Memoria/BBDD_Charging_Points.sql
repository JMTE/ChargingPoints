CREATE DATABASE  IF NOT EXISTS `charging_points` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `charging_points`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: charging_points
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `baterias`
--

DROP TABLE IF EXISTS `baterias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baterias` (
  `id_bateria` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `densidad_energetica` varchar(30) DEFAULT NULL,
  `ciclo_de_vida` int NOT NULL,
  PRIMARY KEY (`id_bateria`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baterias`
--

LOCK TABLES `baterias` WRITE;
/*!40000 ALTER TABLE `baterias` DISABLE KEYS */;
INSERT INTO `baterias` VALUES (1,'Níquel-cadmio (NiCd)','30-80 Wh/kg',500),(2,'Ion-litio (LiCoO2)','100-250 Wh/Kg',1200),(3,'Ion-litio con cátodo de LiFePO4',' 90-100 Wh/Kg',2000),(4,'Polímero de litio (LiPo)',' 300 Wh/Kg',1000);
/*!40000 ALTER TABLE `baterias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conector_en_estacion`
--

DROP TABLE IF EXISTS `conector_en_estacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conector_en_estacion` (
  `id_estacion` int NOT NULL,
  `id_conector` int NOT NULL,
  PRIMARY KEY (`id_estacion`,`id_conector`),
  KEY `id_conector` (`id_conector`),
  CONSTRAINT `conector_en_estacion_ibfk_1` FOREIGN KEY (`id_estacion`) REFERENCES `estaciones` (`id_estacion`),
  CONSTRAINT `conector_en_estacion_ibfk_2` FOREIGN KEY (`id_conector`) REFERENCES `conectores` (`id_conector`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conector_en_estacion`
--

LOCK TABLES `conector_en_estacion` WRITE;
/*!40000 ALTER TABLE `conector_en_estacion` DISABLE KEYS */;
INSERT INTO `conector_en_estacion` VALUES (21,1),(21,2),(22,3),(21,4),(22,4),(21,5);
/*!40000 ALTER TABLE `conector_en_estacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conectores`
--

DROP TABLE IF EXISTS `conectores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conectores` (
  `id_conector` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id_conector`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conectores`
--

LOCK TABLES `conectores` WRITE;
/*!40000 ALTER TABLE `conectores` DISABLE KEYS */;
INSERT INTO `conectores` VALUES (1,'Schuko','Con este tipo de toma se experimenta una recarga lenta que se llevará muchas horas para recargar por completo un eléctrico (en un híbrido enchufable puede ser más asequible). Por lo tanto es más recomendable para bicicletas o motos eléctricas, como mucho para algún modelo pequeño del estilo del Renault Twizy.'),(2,'SAE J1772 (Tipo 1)','Por un lado se puede hacer una carga lenta de hasta 16 amperios y por el otro se pueden alcanzar los 80 amperios de la recarga rápida (hasta 19,2 kW).'),(3,'Mennekes (Tipo 2)','Se trata de uno de los enchufes más comunes y está presente en un buen número de eléctricos hechos por fabricantes europeos (principalmente los grandes alemanes). Marcas como BMW, Mercedes o Volkswagen (incluyendo marcas como Audi o Porsche) lo usan, aunque también está presente en otras como Renault, Volvo o Tesla.'),(4,'CHAdeMo','En la mayoría de modelos donde va montado también se acepta el Tipo 1 y son algunos como los Nissan Leaf y e-NV200, los Mitsubishi Outlander e iMiev o el Kia Soul eléctrico, entre otros.'),(5,'combinado CSS','La clave del CSS es su potencial. En corriente alterna ya podría llegar hasta los 63 amperios y 43 kW como el Mennekes, mientras que en corriente continua podría alcanzar una recarga rápida de 100 kW');
/*!40000 ALTER TABLE `conectores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estaciones`
--

DROP TABLE IF EXISTS `estaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estaciones` (
  `id_estacion` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(30) NOT NULL,
  `numero_puntos_carga` int NOT NULL,
  `provincia` varchar(30) NOT NULL,
  `cpostal` int NOT NULL,
  PRIMARY KEY (`id_estacion`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estaciones`
--

LOCK TABLES `estaciones` WRITE;
/*!40000 ALTER TABLE `estaciones` DISABLE KEYS */;
INSERT INTO `estaciones` VALUES (21,'C/Entrepinares bajo',4,'Barcelona',87387),(22,'C/Memorias de Africa 6',2,'Ciudad Real',63563);
/*!40000 ALTER TABLE `estaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfiles` (
  `id_perfil` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (1,'Administrador','ADMIN'),(2,'Empresa','EMPRE'),(3,'Cliente','CLIEN');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id_reserva` int NOT NULL AUTO_INCREMENT,
  `fecha_reserva` date DEFAULT NULL,
  `fecha_servicio` date DEFAULT NULL,
  `estado` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `id_estacion` int NOT NULL,
  `username` varchar(9) DEFAULT NULL,
  `horas_carga` decimal(9,2) NOT NULL,
  `precio_total` decimal(9,2) NOT NULL,
  `pagado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_reserva`),
  KEY `id_estacion` (`id_estacion`),
  KEY `dni` (`username`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`id_estacion`) REFERENCES `estaciones` (`id_estacion`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`username`) REFERENCES `usuarios` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (63,'2022-05-14','2022-05-27','Pendiente','Mennekes (Tipo 2)',22,'53162796A',2.00,4.60,'No'),(64,'2022-05-14','2022-05-31','Pendiente','CHAdeMo',22,'53162796A',1.00,8.60,'No'),(65,'2022-05-15','2022-05-16','Terminada','combinado CSS',21,'53162796A',1.00,13.40,'No'),(71,'2022-05-16','2022-05-27','Pendiente','CHAdeMo',22,'53162796C',2.00,15.60,'No'),(72,'2022-05-16','2022-06-04','Pendiente','CHAdeMo',22,'53162796C',1.00,17.40,'No'),(73,'2022-05-16','2022-06-03','Pendiente','CHAdeMo',22,'53162796C',1.00,17.80,'No'),(74,'2022-05-16','2022-05-22','Pendiente','combinado CSS',21,'53162796C',1.00,9.00,'No');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_perfiles`
--

DROP TABLE IF EXISTS `usuario_perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_perfiles` (
  `username` varchar(9) NOT NULL,
  `ID_PERFIL` int NOT NULL,
  PRIMARY KEY (`username`,`ID_PERFIL`),
  KEY `ID_PERFIL` (`ID_PERFIL`),
  CONSTRAINT `usuario_perfiles_ibfk_1` FOREIGN KEY (`username`) REFERENCES `usuarios` (`username`),
  CONSTRAINT `usuario_perfiles_ibfk_2` FOREIGN KEY (`ID_PERFIL`) REFERENCES `perfiles` (`id_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_perfiles`
--

LOCK TABLES `usuario_perfiles` WRITE;
/*!40000 ALTER TABLE `usuario_perfiles` DISABLE KEYS */;
INSERT INTO `usuario_perfiles` VALUES ('12345678a',1),('53162796B',2),('53162796D',2),('53162796A',3),('53162796C',3);
/*!40000 ALTER TABLE `usuario_perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `username` varchar(9) NOT NULL,
  `password` varchar(200) NOT NULL,
  `enabled` int DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `matricula` varchar(7) DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  `fecha_ultima_carga` date DEFAULT NULL,
  `numero_cargas` int DEFAULT NULL,
  `id_estacion` int DEFAULT NULL,
  `provincia` varchar(30) NOT NULL,
  `cpostal` int NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `matricula` (`matricula`),
  KEY `fk_id_estacion` (`id_estacion`),
  CONSTRAINT `fk_id_estacion` FOREIGN KEY (`id_estacion`) REFERENCES `estaciones` (`id_estacion`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `vehiculos` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('12345678a','$2a$10$/eyew6OYPyXzczV44lWyuu3AqJ/BocyUpbx2csW8Brj8FHA3fA2X2',1,'Jose Maria','Tenreiro Eiranova','C/Cerro','jose@gmail.com',NULL,'2019-06-08',NULL,0,NULL,'A Coruña',15009),('53162796A','$2a$10$li9NOF.CPwSaieDwVLSaOedBdJIBdB0h0/02oYXTIPOaRcxVty1Ru',1,'Agapito','Fernandez Lopez','C/Margaritas 5 8D','cliente1@gmail.com','7823DAD','2022-05-14',NULL,0,NULL,'Burgos',63573),('53162796B','$2a$10$CkFAIFOVyiVuLgBCAf5/9u/SDEAwQvh7G6uR0l.Xq4nXlXR.iLeRW',1,'Restaurante El Paso','Antonio Lopez','C/Entrepinares bajo','empresa1@gmail.com',NULL,'2022-05-14',NULL,0,21,'Barcelona',87387),('53162796C','$2a$10$3pp6a5r.Bu/GRYxurHiWx.ohu5SzcfMA82l/Nz6FXs2zsiAfh0sh2',1,'Gustavo','Adolfo Becker','C/Las Delicias N8','cliente2@gmail.com','6736GMD','2022-05-14',NULL,0,NULL,'Navarra',87283),('53162796D','$2a$10$srWYZH06mGUxiLHyQr/HBeueO.4j4HxZHxju8j8AtakY.oqWJNtKO',1,'Taller Mecanico Tuerca','Juan Jovellanos','C/Memorias de Africa 6','empresa2@gmail.com',NULL,'2022-05-14',NULL,0,22,'Ciudad Real',63563);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos` (
  `matricula` varchar(7) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `potencia` decimal(9,2) NOT NULL,
  `autonomia` int DEFAULT NULL,
  `id_conector` int DEFAULT NULL,
  `id_bateria` int DEFAULT NULL,
  PRIMARY KEY (`matricula`),
  KEY `id_conector` (`id_conector`),
  KEY `id_bateria` (`id_bateria`),
  CONSTRAINT `vehiculos_ibfk_1` FOREIGN KEY (`id_conector`) REFERENCES `conectores` (`id_conector`),
  CONSTRAINT `vehiculos_ibfk_2` FOREIGN KEY (`id_bateria`) REFERENCES `baterias` (`id_bateria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculos`
--

LOCK TABLES `vehiculos` WRITE;
/*!40000 ALTER TABLE `vehiculos` DISABLE KEYS */;
INSERT INTO `vehiculos` VALUES ('6736GMD','Nissan Leaf',234.00,653,5,4),('7823DAD','Seat Ateca',233.00,23,4,2);
/*!40000 ALTER TABLE `vehiculos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 20:27:02
