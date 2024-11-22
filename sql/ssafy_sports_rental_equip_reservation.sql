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
-- Table structure for table `equip_reservation`
--

DROP TABLE IF EXISTS `equip_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equip_reservation` (
  `res_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  `equip_id` int NOT NULL,
  `res_start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `res_cost` int NOT NULL,
  PRIMARY KEY (`res_id`),
  KEY `e_user_id_idx` (`user_id`),
  KEY `e_equip_id_idx` (`equip_id`),
  CONSTRAINT `e_equip_id` FOREIGN KEY (`equip_id`) REFERENCES `equip` (`equip_id`),
  CONSTRAINT `e_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equip_reservation`
--

LOCK TABLES `equip_reservation` WRITE;
/*!40000 ALTER TABLE `equip_reservation` DISABLE KEYS */;
INSERT INTO `equip_reservation` VALUES (1,'kdy',1,'2024-11-21 07:34:26',6000),(2,'kdy',3,'2024-11-21 07:35:36',4000),(3,'kdy',5,'2024-11-21 07:35:36',5000),(4,'ksh',6,'2024-11-21 07:36:24',3000),(5,'ksh',7,'2024-11-21 07:36:24',2000),(6,'ksh',8,'2024-11-21 07:36:24',1000),(7,'hsw',22,'2024-11-21 23:42:06',3000),(8,'hsw',15,'2024-11-21 23:42:06',2000),(9,'hsw',12,'2024-11-21 23:42:06',4000),(10,'pjh',13,'2024-11-21 23:42:06',2000),(14,'pjh',10,'2024-11-21 23:42:06',1000);
/*!40000 ALTER TABLE `equip_reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-22  8:56:09
