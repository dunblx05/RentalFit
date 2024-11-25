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
  `equip_type` varchar(100) NOT NULL,
  `equip_price` int NOT NULL,
  `equip_detail` varchar(500) DEFAULT NULL,
  `equip_img` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`equip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equip`
--

LOCK TABLES `equip` WRITE;
/*!40000 ALTER TABLE `equip` DISABLE KEYS */;
INSERT INTO `equip` VALUES (1,'야구배트','야구',3000,'야구에서 투수가 던진 공을 치기 위해 타자가 쓰는 방망이.','bat.png'),(2,'야구공','야구',2000,'야구공은 야구에서 쓰이는 공이다. 실밥이 108개, 중량이 약 141.7~148.8g, 지름은 약 7.3~7.5cm로 제조사마다 다르다.','baseball.png'),(3,'축구공','축구',2000,'축구 경기에서 사용하는 경기 용구로, 둘레는 약 68cm, 무게는 약 410~450g의 공이다.','soccerball.png'),(4,'축구화','축구',4000,'축구화는 축구를 할 때 신는 운동화이다. 축구 경기장의 잔디에 미끄러지지 않도록 밑창에 스터드를 장착한 게 대부분이다.','soccer_shoes.png'),(5,'축구글러브','축구',2500,'골키퍼 장갑은 축구 골키퍼에게 있어서 축구화보다도 더 중요한 물건이다.','soccer_glove.png'),(6,'배드민턴채','배드민턴',3000,'배드민턴 라켓은 가벼운 소재(주로 카본, 알루미늄)로 만들어져 있으며, 빠르고 정교한 스윙을 위해 설계된 스포츠 도구입니다.','badminton_chae.png'),(7,'셔틀콕','배드민턴',500,'셔틀콕은 배드민턴에서 사용되는 공으로, 앞부분은 둥근 코르크로 되어 있고 뒷부분은 깃털(천연 깃털 또는 합성 재질)로 구성됩니다.','shuttlecock.png'),(8,'탁구채','탁구',1000,'탁구 라켓은 탁구 경기에 사용되는 도구로, 나무로 만든 판형의 베이스(블레이드)와 양면에 붙은 고무로 구성됩니다.','pingpong_chae.png'),(9,'탁구공','탁구',500,'탁구공은 탁구 경기에 사용되는 둥근 공으로, 직경 40mm, 무게 약 2.7g의 플라스틱 소재로 만들어집니다.','pingpong_ball.png'),(10,'농구공','농구',1000,'농구공은 농구 경기에 사용되는 공으로, 둥글고 표면이 고무나 합성가죽, 천연가죽으로 만들어져 있습니다.','basketball.png'),(11,'농구화','농구',5000,'농구화는 농구 경기에서 발을 보호하고 최적의 퍼포먼스를 지원하기 위해 설계된 운동화입니다.','basketball_shoes.png'),(12,'야구글러브','야구',3000,'야구글러브는 야구 경기에서 공을 잡기 위해 사용하는 가죽으로 만든 장비입니다. 포지션에 따라 글러브의 크기와 디자인이 달라지며, 투수, 포수, 내야수, 외야수용으로 구분됩니다.','baseball_glove.png');
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

-- Dump completed on 2024-11-25 10:44:07
