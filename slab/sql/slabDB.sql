CREATE DATABASE  IF NOT EXISTS `slab` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `slab`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: slab
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `agarres`
--

DROP TABLE IF EXISTS `agarres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agarres` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agarres`
--

LOCK TABLES `agarres` WRITE;
/*!40000 ALTER TABLE `agarres` DISABLE KEYS */;
INSERT INTO `agarres` VALUES (1,'inicio'),(2,'paso intermedio'),(3,'fin');
/*!40000 ALTER TABLE `agarres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bloques`
--

DROP TABLE IF EXISTS `bloques`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bloques` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `rocodromos_id` bigint NOT NULL,
  `grados_id` bigint NOT NULL,
  `route_setters_id` bigint NOT NULL,
  `fotos_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bloques_rocodromos_idx` (`rocodromos_id`),
  KEY `fk_bloques_grados1_idx` (`grados_id`),
  KEY `fk_bloques_route_setters1_idx` (`route_setters_id`),
  KEY `fk_bloques_fotos2_idx` (`fotos_id`),
  CONSTRAINT `fk_bloques_fotos2` FOREIGN KEY (`fotos_id`) REFERENCES `fotos` (`id`),
  CONSTRAINT `fk_bloques_grados1` FOREIGN KEY (`grados_id`) REFERENCES `grados` (`id`),
  CONSTRAINT `fk_bloques_rocodromos` FOREIGN KEY (`rocodromos_id`) REFERENCES `rocodromos` (`id`),
  CONSTRAINT `fk_bloques_route_setters1` FOREIGN KEY (`route_setters_id`) REFERENCES `route_setters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bloques`
--

LOCK TABLES `bloques` WRITE;
/*!40000 ALTER TABLE `bloques` DISABLE KEYS */;
INSERT INTO `bloques` VALUES (2,'Bloque de prueba',1,1,1,1),(3,'Segundo bloque de prueba',1,2,1,2);
/*!40000 ALTER TABLE `bloques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bloques_has_escalador`
--

DROP TABLE IF EXISTS `bloques_has_escalador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bloques_has_escalador` (
  `bloques_id` bigint NOT NULL,
  `escalador_id` bigint NOT NULL,
  PRIMARY KEY (`bloques_id`,`escalador_id`),
  KEY `fk_bloques_has_escalador_escalador1_idx` (`escalador_id`),
  KEY `fk_bloques_has_escalador_bloques1_idx` (`bloques_id`),
  CONSTRAINT `fk_bloques_has_escalador_bloques1` FOREIGN KEY (`bloques_id`) REFERENCES `bloques` (`id`),
  CONSTRAINT `fk_bloques_has_escalador_escalador1` FOREIGN KEY (`escalador_id`) REFERENCES `escaladores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bloques_has_escalador`
--

LOCK TABLES `bloques_has_escalador` WRITE;
/*!40000 ALTER TABLE `bloques_has_escalador` DISABLE KEYS */;
INSERT INTO `bloques_has_escalador` VALUES (2,1);
/*!40000 ALTER TABLE `bloques_has_escalador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escaladores`
--

DROP TABLE IF EXISTS `escaladores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `escaladores` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escaladores`
--

LOCK TABLES `escaladores` WRITE;
/*!40000 ALTER TABLE `escaladores` DISABLE KEYS */;
INSERT INTO `escaladores` VALUES (1,'Escalador de prueba');
/*!40000 ALTER TABLE `escaladores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotos`
--

DROP TABLE IF EXISTS `fotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotos`
--

LOCK TABLES `fotos` WRITE;
/*!40000 ALTER TABLE `fotos` DISABLE KEYS */;
INSERT INTO `fotos` VALUES (1,'imagen1.jpg'),(2,'imagen2.jpg'),(3,'imagen3.jpg');
/*!40000 ALTER TABLE `fotos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotos_has_agarres`
--

DROP TABLE IF EXISTS `fotos_has_agarres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotos_has_agarres` (
  `fotos_id` bigint NOT NULL,
  `agarres_id` bigint NOT NULL,
  `coordenadas` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`fotos_id`,`agarres_id`,`coordenadas`),
  KEY `fk_fotos_has_agarres_agarres1_idx` (`agarres_id`),
  KEY `fk_fotos_has_agarres_fotos1_idx` (`fotos_id`),
  CONSTRAINT `fk_fotos_has_agarres_agarres1` FOREIGN KEY (`agarres_id`) REFERENCES `agarres` (`id`),
  CONSTRAINT `fk_fotos_has_agarres_fotos1` FOREIGN KEY (`fotos_id`) REFERENCES `fotos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotos_has_agarres`
--

LOCK TABLES `fotos_has_agarres` WRITE;
/*!40000 ALTER TABLE `fotos_has_agarres` DISABLE KEYS */;
INSERT INTO `fotos_has_agarres` VALUES (1,1,'14 16 52'),(2,1,'59 45 13'),(1,2,'16 15 84'),(1,2,'17 16 85'),(2,2,'20 15 98'),(2,2,'78 45 12'),(2,2,'78 45 19'),(1,3,'80 45 45'),(2,3,'85 85 65');
/*!40000 ALTER TABLE `fotos_has_agarres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grados`
--

DROP TABLE IF EXISTS `grados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grados` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `grado` varchar(6) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grados`
--

LOCK TABLES `grados` WRITE;
/*!40000 ALTER TABLE `grados` DISABLE KEYS */;
INSERT INTO `grados` VALUES (1,'5A'),(2,'6A'),(3,'6B'),(4,'6C'),(5,'7A'),(6,'7A+'),(7,'7B'),(8,'7B+');
/*!40000 ALTER TABLE `grados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rocodromos`
--

DROP TABLE IF EXISTS `rocodromos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rocodromos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `ubicacion` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rocodromos`
--

LOCK TABLES `rocodromos` WRITE;
/*!40000 ALTER TABLE `rocodromos` DISABLE KEYS */;
INSERT INTO `rocodromos` VALUES (1,'Roco de prueba','Bermeo');
/*!40000 ALTER TABLE `rocodromos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_setters`
--

DROP TABLE IF EXISTS `route_setters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route_setters` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_setters`
--

LOCK TABLES `route_setters` WRITE;
/*!40000 ALTER TABLE `route_setters` DISABLE KEYS */;
INSERT INTO `route_setters` VALUES (1,'Route Setter de prueba'),(2,'RS1'),(3,'RS2');
/*!40000 ALTER TABLE `route_setters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'slab'
--

--
-- Dumping routines for database 'slab'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-12  9:42:55
