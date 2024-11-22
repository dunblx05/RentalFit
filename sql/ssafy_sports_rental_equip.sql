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
-- Table structure for table `equip`
--

DROP TABLE IF EXISTS `equip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equip` (
  `equip_id` int NOT NULL AUTO_INCREMENT,
  `equip_name` varchar(100) NOT NULL,
  `equip_play_type` varchar(100) NOT NULL,
  `equip_use_type` varchar(100) NOT NULL,
  `equip_cost` int NOT NULL,
  `equip_res_time` int NOT NULL,
  `equip_img` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`equip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equip`
--

LOCK TABLES `equip` WRITE;
/*!40000 ALTER TABLE `equip` DISABLE KEYS */;
INSERT INTO `equip` VALUES (1,'짱샌야구배트','야구','야구배트',3000,2,'strong_bat'),(2,'잘잡아글러브','야구','야구글러브',3000,2,'glove'),(3,'빨라야구공','야구','야구공',3000,2,'baseball'),(4,'약한야구배트','야구','야구배트',2000,2,'weak_bat'),(5,'짱샌야구배트','야구','야구배트',3000,2,'strong_bat'),(6,'짱샌야구배트','야구','야구배트',3000,2,'strong_bat'),(7,'짱샌야구배트','야구','야구배트',3000,2,'strong_bat'),(8,'짱샌야구배트','야구','야구배트',3000,2,'strong_bat'),(9,'약한야구배트','야구','야구배트',2000,2,'weak_bat'),(10,'약한야구배트','야구','야구배트',2000,2,'weak_bat'),(11,'약한야구배트','야구','야구배트',2000,2,'weak_bat'),(12,'축구공1','축구','축구공',3000,2,'soccer_ball'),(13,'축구공2','축구','축구공',3000,2,'soccer_ball'),(14,'축구공3','축구','축구공',3000,2,'soccer_ball'),(15,'탁구채1','탁구','탁구채',1000,2,'pingpong_chae'),(16,'탁구채2','탁구','탁구채',2000,2,'pingpong_chae'),(17,'탁구공1','탁구','탁구공',1500,2,'pingpong_ball'),(18,'탁구공2','탁구','탁구공',1500,2,'pingpong_ball'),(19,'배드민턴채1','배드민턴','배드민턴채',500,3,'badminton_chae'),(20,'배드민턴채2','배드민턴','배드민턴채',500,3,'badminton_chae'),(21,'셔틀콕1','배드민턴','셔틀콕',500,3,'badminton_ball'),(22,'셔틀콕1','배드민턴','셔틀콕',500,3,'badminton_ball');
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

-- Dump completed on 2024-11-22  8:56:09
