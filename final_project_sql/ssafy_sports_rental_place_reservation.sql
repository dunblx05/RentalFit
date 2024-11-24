-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: ssafy_sports_rental
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `place_reservation`
--

DROP TABLE IF EXISTS `place_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place_reservation` (
  `res_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  `place_id` int NOT NULL,
  `res_start_time` datetime NOT NULL,
  `res_end_time` datetime NOT NULL,
  `res_cost` int NOT NULL,
  PRIMARY KEY (`res_id`),
  KEY `p_user_id_idx` (`user_id`),
  KEY `p_place_id_idx` (`place_id`),
  CONSTRAINT `p_place_id` FOREIGN KEY (`place_id`) REFERENCES `place` (`place_id`),
  CONSTRAINT `p_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place_reservation`
--

LOCK TABLES `place_reservation` WRITE;
/*!40000 ALTER TABLE `place_reservation` DISABLE KEYS */;
INSERT INTO `place_reservation` VALUES (1,'kdy',1,'2024-11-21 12:00:00','2024-11-21 12:30:00',3000),(2,'kdy',4,'2024-11-21 14:00:00','2024-11-21 14:30:00',5000),(3,'ksh',2,'2024-11-21 15:00:00','2024-11-21 15:30:00',6000),(4,'ksh',4,'2024-11-21 17:00:00','2024-11-21 18:00:00',7000),(5,'kdy',3,'2024-11-21 09:00:00','2024-11-21 09:30:00',4000),(6,'kdy',4,'2024-11-21 16:00:00','2024-11-21 16:30:00',5000),(7,'kdy',4,'2024-11-21 16:00:00','2024-11-21 16:30:00',5000),(8,'kdy',4,'2024-11-21 16:00:00','2024-11-21 16:30:00',5000),(9,'kdy',4,'2024-11-21 16:00:00','2024-11-21 16:30:00',5000),(10,'kdy',4,'2024-12-21 16:00:00','2024-12-21 16:30:00',5000),(11,'kdy',1,'2024-11-22 12:00:00','2024-11-22 12:30:00',3000),(12,'kdy',3,'2024-11-22 05:23:52','2024-11-22 05:23:52',4000),(13,'ksh',2,'2024-11-22 15:00:00','2024-11-22 15:30:00',6000),(14,'hsw',5,'2024-11-22 16:00:00','2024-11-22 16:30:00',5000);
/*!40000 ALTER TABLE `place_reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-24 18:44:25
