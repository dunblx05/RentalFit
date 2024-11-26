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
-- Table structure for table `equip_order`
--

DROP TABLE IF EXISTS `equip_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equip_order` (
  `equip_order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `equip_order_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`equip_order_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equip_order`
--

LOCK TABLES `equip_order` WRITE;
/*!40000 ALTER TABLE `equip_order` DISABLE KEYS */;
INSERT INTO `equip_order` VALUES (1,'kdy','2024-11-22 00:55:55'),(2,'kdy','2024-11-22 00:55:55'),(3,'ksh','2024-11-22 00:55:55'),(4,'ksh','2024-11-22 00:55:55'),(5,'hsw','2024-11-22 00:55:55'),(6,'kdy','2024-11-22 08:05:33'),(7,'kdy','2024-11-22 08:08:28'),(8,'kdy','2024-11-25 04:27:33'),(9,'kdy','2024-11-25 04:28:46'),(10,'kdy','2024-11-25 04:30:27'),(11,'kdy','2024-11-25 05:31:41'),(12,'kdy','2024-11-25 05:54:14'),(13,'kdy','2024-11-25 06:16:23'),(14,'Dxn8zsz35aZXWxmIKMyawHaJpCZ2','2024-11-25 23:43:02'),(15,'VfPMbRxYodNdt5aNwfpq9r3vjVe2','2024-11-26 01:43:16'),(16,'VfPMbRxYodNdt5aNwfpq9r3vjVe2','2024-11-26 01:47:35'),(17,'VfPMbRxYodNdt5aNwfpq9r3vjVe2','2024-11-26 01:48:08'),(18,'VfPMbRxYodNdt5aNwfpq9r3vjVe2','2024-11-26 01:48:41'),(19,'VfPMbRxYodNdt5aNwfpq9r3vjVe2','2024-11-26 01:49:45'),(20,'VfPMbRxYodNdt5aNwfpq9r3vjVe2','2024-11-26 01:50:06'),(21,'VfPMbRxYodNdt5aNwfpq9r3vjVe2','2024-11-26 01:50:41'),(22,'kdy','2024-11-26 01:54:55'),(23,'fUiSgqXLSNWYoTVEKQzuWppLDr32','2024-11-26 04:55:50');
/*!40000 ALTER TABLE `equip_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26 15:05:36
