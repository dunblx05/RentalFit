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
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place` (
  `place_id` int NOT NULL AUTO_INCREMENT,
  `place_name` varchar(100) NOT NULL,
  `place_people` int DEFAULT NULL,
  `place_location` varchar(100) DEFAULT NULL,
  `place_type` varchar(100) DEFAULT NULL,
  `place_cost` int DEFAULT NULL,
  `place_img` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,'라이온즈파크',30,'대구광역시','야구장',1000,'baseball_park1.png'),(2,'챔피언스필드',30,'광주광역시','야구장',1000,'baseball_park2.png'),(3,'잠실야구장',30,'서울특별시','야구장',1000,'baseball_park3.png'),(4,'고척돔구장',30,'서울특별시','야구장',1000,'baseball_park4.png'),(5,'올드트래포드',30,'구미시','축구장',2000,'soccer_place1.png'),(6,'대구시민구장',30,'대구광역시','축구장',2000,'soccer_place2.png'),(7,'광주탁구장',30,'광주광역시','탁구장',500,'pingpong_place1.png'),(8,'구미수영장',20,'구미시','수영장',850,'swimming_pool1.png'),(9,'대구수영장',10,'대구광역시','수영장',100,'swimming_pool2.png'),(10,'서울수영장',100,'서울특별시','수영장',3000,'swimming_pool3.png'),(11,'대구농구장',40,'대구광역시','농구장',5000,'basketball_court.png');
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
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
