-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_inventario
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `almacen` (
  `id_almacen` int NOT NULL AUTO_INCREMENT,
  `almacen` varchar(100) NOT NULL,
  PRIMARY KEY (`id_almacen`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
INSERT INTO `almacen` VALUES (1,'ALFA'),(2,'BETA');
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100) NOT NULL,
  `deuda_pendiente` decimal(10,2) NOT NULL DEFAULT '0.00',
  `credito_disponible` decimal(10,2) NOT NULL DEFAULT '0.00',
  `calle` varchar(50) NOT NULL,
  `numero_casa` varchar(10) NOT NULL,
  `colonia` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL DEFAULT 'N/A',
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'José','Ramírez','Pineda',300.00,500.00,'Blvd. Juan Navarrete','#125','Valle Verde','+526624444844'),(2,'Irasema','Lopez','Hernandez',0.00,0.00,'Sahuaripa','100','Las Flores','6624030591'),(3,'Santiago','López','Martínez',8.00,1050.00,'Blvd. Juan Navarrete','#125','Valle Verde','+526624444844'),(4,'José Juancho','López','Moreno',300.00,1000.00,'Blvd. Juan Navarrete','#125','Valle Verde','+526624444844'),(5,'Chico','Sanchez','Alvarez',0.00,40000000.00,'Blvd. Juan Navarrete','#125','Valle Verde','+526624444844'),(6,'Pablo','Arellano','Chavez',100.00,1000.00,'Blvd. Juan Navarrete','#125','Valle Verde','+526624444844'),(7,'Irasema','Lopez','Hernandez',0.00,0.00,'Sahuaripa','100','Las Flores','6624030591'),(8,'Luis','Casas','Hernandez',0.00,0.00,'Dos','103','Choyal','663432323'),(9,'Andres','Montaño','Silva',0.00,0.00,'Tres','52','Monteverde','66324546664'),(10,'Santiago','Hernandez','Arriaga',0.00,0.00,'Uno','32','La Joya','6620934343'),(11,'Prueba','Prueba','Prueba',0.00,0.00,'Prueba','Prueba','Prueba',''),(12,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba','1'),(13,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba',''),(14,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba','prueba'),(15,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba',''),(16,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba',''),(17,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba','prueba'),(18,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba','prueba'),(19,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba',''),(20,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba',''),(21,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba',''),(22,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba',''),(23,'prueba','prueba','prueba',0.00,0.00,'prueba','prueba','prueba','');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_venta`
--

DROP TABLE IF EXISTS `detalles_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_venta` (
  `id_detalles_venta` int NOT NULL AUTO_INCREMENT,
  `id_venta` int NOT NULL,
  `id_producto` int NOT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id_detalles_venta`),
  KEY `id_venta` (`id_venta`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalles_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`),
  CONSTRAINT `detalles_venta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_venta`
--

LOCK TABLES `detalles_venta` WRITE;
/*!40000 ALTER TABLE `detalles_venta` DISABLE KEYS */;
INSERT INTO `detalles_venta` VALUES (1,1,1,1);
/*!40000 ALTER TABLE `detalles_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos_credito`
--

DROP TABLE IF EXISTS `pagos_credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos_credito` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `cantidad_pagada` decimal(10,2) NOT NULL,
  `fecha` date NOT NULL,
  `id_venta` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_pago`),
  KEY `id_cliente` (`id_cliente`),
  KEY `fk_id_venta` (`id_venta`),
  CONSTRAINT `fk_id_venta` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`),
  CONSTRAINT `pagos_credito_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos_credito`
--

LOCK TABLES `pagos_credito` WRITE;
/*!40000 ALTER TABLE `pagos_credito` DISABLE KEYS */;
INSERT INTO `pagos_credito` VALUES (1,1,1000.00,'2024-08-30',1);
/*!40000 ALTER TABLE `pagos_credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `id_almacen` int DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `id_tamano` int DEFAULT NULL,
  `precio_venta` decimal(10,2) NOT NULL,
  `costo_empresa` decimal(10,2) NOT NULL,
  `fecha_llegada` date NOT NULL,
  `cantidad_inventario` int NOT NULL,
  `id_tipo_producto` int DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `id_almacen` (`id_almacen`),
  KEY `id_tamano` (`id_tamano`),
  KEY `fk_tipo_producto` (`id_tipo_producto`),
  CONSTRAINT `fk_tipo_producto` FOREIGN KEY (`id_tipo_producto`) REFERENCES `tipo_producto` (`id_tipo_producto`) ON DELETE CASCADE,
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_almacen`) REFERENCES `almacen` (`id_almacen`),
  CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`id_tamano`) REFERENCES `tamano` (`id_tamano`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,1,'Sillón color azul',3,8000.00,3000.00,'2024-08-20',5,1),(4,1,'Sillón Individual Azul',2,3000.00,1000.00,'2024-08-19',1,1),(7,1,'Lampara luz led',1,100.00,20.00,'2024-09-17',20,2),(9,2,'Refrigerador industrial',3,30000.00,8000.00,'2024-09-23',5,2),(10,1,'Cafetera Oyster',1,900.00,300.00,'2024-10-02',15,2),(11,2,'Silla de madera',1,100.00,15.00,'2024-10-03',5,1),(12,1,'Estereo',1,400.00,100.00,'2024-10-09',15,2),(13,1,'Teclado Yamaha',1,10000.00,2000.00,'2024-10-12',5,2);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tamano`
--

DROP TABLE IF EXISTS `tamano`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tamano` (
  `id_tamano` int NOT NULL AUTO_INCREMENT,
  `tamano` varchar(100) NOT NULL,
  PRIMARY KEY (`id_tamano`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tamano`
--

LOCK TABLES `tamano` WRITE;
/*!40000 ALTER TABLE `tamano` DISABLE KEYS */;
INSERT INTO `tamano` VALUES (1,'chico'),(2,'mediano'),(3,'grande'),(4,'muy grande');
/*!40000 ALTER TABLE `tamano` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_producto` (
  `id_tipo_producto` int NOT NULL AUTO_INCREMENT,
  `nombre_tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'Muebles'),(2,'Electrodomésticos');
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id_venta` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL,
  `fecha` date NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `pago_inicial` decimal(10,2) DEFAULT NULL,
  `pago_credito` decimal(10,2) DEFAULT NULL,
  `id_producto` int NOT NULL DEFAULT '0',
  `cantidad` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_venta`),
  KEY `id_cliente` (`id_cliente`),
  KEY `fk_id_producto` (`id_producto`),
  CONSTRAINT `fk_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,2,'2024-09-16',7000.00,7723.00,278.00,1,1),(2,1,'2024-09-16',0.00,1000.00,0.00,1,2),(3,6,'2024-09-18',100.00,100.00,0.00,1,1),(6,1,'2024-10-12',10000.00,10000.00,0.00,13,1),(7,1,'2024-10-12',400.00,400.00,0.00,12,1),(8,3,'2024-10-12',900.00,900.00,0.00,10,1),(9,3,'2024-10-12',100.00,100.00,0.00,7,1),(10,3,'2024-10-12',400.00,300.00,100.00,12,1);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_inventario'
--

--
-- Dumping routines for database 'bd_inventario'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-13  8:25:16
