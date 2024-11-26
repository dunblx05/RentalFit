-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ssafy_sports_rental
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `equip_order_detail`
--

DROP TABLE IF EXISTS `equip_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equip_order_detail` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `equip_order_id` int DEFAULT NULL,
  `equip_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `equip_order_id_idx` (`equip_order_id`),
  KEY `equip_id_idx` (`equip_id`),
  CONSTRAINT `equip_id` FOREIGN KEY (`equip_id`) REFERENCES `equip` (`equip_id`),
  CONSTRAINT `equip_order_id` FOREIGN KEY (`equip_order_id`) REFERENCES `equip_order` (`equip_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equip_order_detail`
--

LOCK TABLES `equip_order_detail` WRITE;
/*!40000 ALTER TABLE `equip_order_detail` DISABLE KEYS */;
INSERT INTO `equip_order_detail` VALUES (1,1,1,5),(2,1,3,2),(3,2,2,2),(4,2,4,3),(5,3,5,4),(6,3,6,5),(7,4,7,1),(8,4,8,2),(9,5,9,3),(10,6,3,3),(11,7,3,4),(12,7,2,5),(13,8,4,5),(14,9,1,6),(15,9,6,6),(16,10,1,4),(17,11,3,3),(18,12,2,1),(19,12,1,1),(20,12,4,6),(21,12,11,5),(22,12,10,4),(23,13,1,4),(24,14,1,5),(25,15,1,2),(26,16,1,1),(27,16,2,1),(28,16,3,1),(29,17,1,1),(30,17,3,1),(31,17,4,1),(32,18,1,1),(33,19,1,1),(34,19,2,1),(35,20,1,1),(36,21,1,3),(37,22,1,3),(38,22,9,4),(39,23,2,1);
/*!40000 ALTER TABLE `equip_order_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26 15:05:37
