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
  `place_detail` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,'라이온즈파크',30,'대구광역시','야구장',1000,'baseball_park1.png','대구광역시 수성구 연호동에 있는 야구장, 대구삼성라이온즈파크는 한국프로야구 소속 삼성 라이온즈 팀의 홈구장으로 사용되고 있는 공공체육시설이다.'),(2,'챔피언스필드',30,'광주광역시','야구장',1000,'baseball_park2.png','프로야구단 해태 타이거즈 및 KIA 타이거즈의 홈구장으로 사용되었던 광주무등경기장 옆에 새로 건립한 야구 경기장으로, KIA 타이거즈의 홈구장으로 사용된다.'),(3,'잠실야구장',30,'서울특별시','야구장',1000,'baseball_park3.png','한국에서 가장 규모가 큰 야구장으로 1982년 7월 15일에 개장되었다. 현재 서울을 연고지로 하는 KBO리그 프로야구팀 두산베어스와 LG트윈스가 홈구장으로 사용하고 있다.'),(4,'고척돔구장',30,'서울특별시','야구장',1000,'baseball_park4.png','사계절 내내 날씨와 상관없이 야구경기와 각종 공연 및 행사를 열 수 있는 한국 최초의 돔(dome) 형태 복합체육문화시설이다. 또한 서울을 연고로 하는 프로야구 구단 키움 히어로즈의 홈구장이기도 하다. '),(5,'올드트래포드',30,'구미시','축구장',2000,'soccer_place1.png','영국 프리미어리그 맨체스터 유나이티드 FC(Manchester United FC)의 경기장이다. 영국 잉글랜드 북서부 맨체스터(Manchester)에 있다.'),(6,'대구시민구장',30,'대구광역시','축구장',2000,'soccer_place2.png','옻골축구장은 대구광역시 북구 구암동 옻골동산 내에 있는 축구장으로 ‘옻골동산 축구장’ 또는 ‘옻골공원 축구장’으로 불리기도 한다.'),(7,'광주탁구장',30,'광주광역시','탁구장',500,'pingpong_place1.png','탁구를 칠 수 있다.'),(8,'구미수영장',20,'구미시','수영장',850,'swimming_pool1.png','수영을 할 수 있다.'),(9,'대구수영장',10,'대구광역시','수영장',100,'swimming_pool2.png','수영을 할 수 있다.'),(10,'서울수영장',100,'서울특별시','수영장',3000,'swimming_pool3.png','수영을 할 수 있다.'),(11,'대구농구장',40,'대구광역시','농구장',5000,'basketball_court.png','대구광역시 북구 산격동에 있는 실내체육관. 대구광역시청 산격청사 (舊 경상북도청) 건물 뒤편 바로 옆에 있다.');
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

-- Dump completed on 2024-11-25 10:44:07
