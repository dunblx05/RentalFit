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
-- Table structure for table `equip`
--

DROP TABLE IF EXISTS `equip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equip` (
  `equip_id` int NOT NULL AUTO_INCREMENT,
  `equip_name` varchar(100) NOT NULL,
  `equip_type` varchar(100) NOT NULL,
  `equip_price` int NOT NULL,
  `equip_detail` varchar(500) DEFAULT NULL,
  `equip_img` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`equip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equip`
--

LOCK TABLES `equip` WRITE;
/*!40000 ALTER TABLE `equip` DISABLE KEYS */;
INSERT INTO `equip` VALUES (1,'야구배트','야구',3000,NULL,'bat.png'),(2,'야구공','야구',2000,NULL,'baseball.png'),(3,'축구공','축구',2000,NULL,'soccerball.png'),(4,'축구화','축구',4000,NULL,'soccer_shoes.png'),(5,'축구글러브','축구',2500,NULL,'soccer_glove.png'),(6,'배드민턴채','배드민턴',3000,NULL,'badminton_chae.png'),(7,'셔틀콕','배드민턴',500,NULL,'shuttlecock.png'),(8,'탁구채','탁구',1000,NULL,'pingpong_chae.png'),(9,'탁구공','탁구',500,NULL,'pingpong_ball.png'),(10,'농구공','농구',1000,NULL,'basketball.png'),(11,'농구화','농구',5000,NULL,'basketball_shoes.png');
/*!40000 ALTER TABLE `equip` ENABLE KEYS */;
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
