-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: exchange
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `user_id` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `nick_name` varchar(20) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `email` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `region` varchar(20) NOT NULL,
  `skill_number` int(11) unsigned NOT NULL,
  `skill_max` int(11) unsigned NOT NULL,
  `recent_log` date NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('bowen','670b14728ad9902aecba32e22fa4f6bd','施博文','最囉嗦',1,'00257111@ntou.edu.tw','1994-05-11','彰化',0,3,'2017-01-05'),('vegetable','670b14728ad9902aecba32e22fa4f6bd','蔡昌廷','菜逃貴',1,'00257114','1994-10-04','新竹',0,3,'2017-01-05'),('yow831102','670b14728ad9902aecba32e22fa4f6bd','羅祐任','柚稚鬼',1,'00257104@ntou.edu.tw','1994-11-02','台北',0,3,'2017-01-05');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classes` (
  `class_code` varchar(20) NOT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`class_code`),
  UNIQUE KEY `class_name` (`class_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES ('SKL','技藝'),('CRD','棋牌'),('CKN','烹飪'),('PLG','程式語言'),('DNC','舞蹈'),('ART','藝術'),('DSN','設計'),('LNG','語言'),('SPT','運動與競技'),('MUS','音樂');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `skill_id` int(11) NOT NULL,
  `comment` varchar(20) NOT NULL,
  `date` date NOT NULL,
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'超棒的','2017-01-05'),(1,'廢物','2017-01-08'),(1,'不錯','2017-01-01');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exchanges`
--

DROP TABLE IF EXISTS `exchanges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exchanges` (
  `skill_a` int(11) NOT NULL,
  `skill_b` int(11) NOT NULL,
  `end_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`skill_a`,`skill_b`),
  KEY `skill_b` (`skill_b`),
  CONSTRAINT `exchanges_ibfk_1` FOREIGN KEY (`skill_a`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `exchanges_ibfk_2` FOREIGN KEY (`skill_b`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchanges`
--

LOCK TABLES `exchanges` WRITE;
/*!40000 ALTER TABLE `exchanges` DISABLE KEYS */;
/*!40000 ALTER TABLE `exchanges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorites` (
  `type_name` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`type_name`,`user_id`),
  KEY `account` (`user_id`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`type_name`) REFERENCES `types` (`type_name`),
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `accounts` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES ('中文 ','vegetable'),('素描','vegetable');
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `skill_id` int(11) NOT NULL,
  `image` varchar(2083) NOT NULL,
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `images_ibfk_1` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,'@URL'),(1,'@URL_1');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitations`
--

DROP TABLE IF EXISTS `invitations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitations` (
  `ivt_sender` int(11) NOT NULL,
  `ivt_receiver` int(11) NOT NULL,
  PRIMARY KEY (`ivt_sender`,`ivt_receiver`),
  KEY `ivt_receiver` (`ivt_receiver`),
  CONSTRAINT `invitations_ibfk_1` FOREIGN KEY (`ivt_sender`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `invitations_ibfk_2` FOREIGN KEY (`ivt_receiver`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitations`
--

LOCK TABLES `invitations` WRITE;
/*!40000 ALTER TABLE `invitations` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `msg_id` int(11) NOT NULL,
  `msg_sender` int(11) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`msg_id`),
  KEY `msg_sender` (`msg_sender`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`msg_sender`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `skill_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `type_name` varchar(20) NOT NULL,
  `intro_expr` text NOT NULL,
  `skill_level` int(11) unsigned NOT NULL,
  `times` int(11) unsigned NOT NULL,
  `attitude_score` int(11) unsigned NOT NULL,
  `profession_score` int(11) unsigned NOT NULL,
  `teaching_score` int(11) unsigned NOT NULL,
  `frequency_score` int(11) unsigned NOT NULL,
  `satisfication_score` int(11) unsigned NOT NULL,
  `warning_tag` tinyint(1) NOT NULL,
  `bad_tag` tinyint(1) NOT NULL,
  PRIMARY KEY (`skill_id`),
  UNIQUE KEY `account` (`user_id`,`type_name`),
  KEY `type_name` (`type_name`),
  CONSTRAINT `skills_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `accounts` (`user_id`),
  CONSTRAINT `skills_ibfk_2` FOREIGN KEY (`type_name`) REFERENCES `types` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'vegetable','吉他','施博文是87',87,87,300,400,321,222,87,0,0),(2,'yow831102','吉他','紅豆',0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `types` (
  `type_code` varchar(20) NOT NULL,
  `type_name` varchar(20) DEFAULT NULL,
  `class_code` varchar(20) NOT NULL,
  PRIMARY KEY (`type_code`),
  UNIQUE KEY `type_name` (`type_name`),
  KEY `class_code` (`class_code`),
  CONSTRAINT `types_ibfk_1` FOREIGN KEY (`class_code`) REFERENCES `classes` (`class_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES ('ART-001','素描','ART'),('ART-002','電繪','ART'),('ART-003','書法','ART'),('ART-004','雕塑','ART'),('ART-005','戲劇','ART'),('ART-006','水彩','ART'),('ART-007','油畫','ART'),('CKN-001','料理','CKN'),('CKN-002','烘焙','CKN'),('CKN-003','調酒','CKN'),('CRD-001','橋牌','CRD'),('CRD-002','象棋','CRD'),('CRD-003','圍棋','CRD'),('CRD-004','跳棋','CRD'),('CRD-005','西洋棋','CRD'),('DNC-001','原住民舞蹈','DNC'),('DNC-002','民俗舞蹈','DNC'),('DNC-003','現代舞','DNC'),('DNC-004','芭蕾舞','DNC'),('DSN-001','髮型設計','DSN'),('DSN-002','廣告設計','DSN'),('DSN-003','服裝設計','DSN'),('LNG-001','英文','LNG'),('LNG-002','中文','LNG'),('LNG-003','日文','LNG'),('LNG-004','德文','LNG'),('LNG-005','泰文','LNG'),('LNG-006','法文','LNG'),('LNG-007','西班牙文','LNG'),('LNG-008','拉丁文','LNG'),('LNG-009','韓文','LNG'),('LNG-010','越南文','LNG'),('LNG-011','希臘文','LNG'),('LNG-012','阿拉伯文','LNG'),('LNG-013','俄文','LNG'),('LNG-014','夏威夷文','LNG'),('LNG-015','挪威文','LNG'),('LNG-016','馬來文','LNG'),('LNG-017','菲律賓文','LNG'),('LNG-018','瑞典文','LNG'),('LNG-019','荷蘭文','LNG'),('MUS-001','歌唱','MUS'),('MUS-002','吉他','MUS'),('MUS-003','鋼琴','MUS'),('MUS-004','小提琴','MUS'),('MUS-005','中提琴','MUS'),('MUS-006','大提琴','MUS'),('MUS-007','口琴','MUS'),('MUS-008','電吉他','MUS'),('MUS-009','爵士鼓','MUS'),('MUS-010','法國號','MUS'),('MUS-011','喇叭','MUS'),('MUS-012','貝斯','MUS'),('MUS-013','定音鼓','MUS'),('MUS-014','笙','MUS'),('MUS-015','蕭','MUS'),('MUS-016','單簧管','MUS'),('MUS-017','豎笛','MUS'),('MUS-018','直笛','MUS'),('MUS-019','嗩吶','MUS'),('MUS-020','二胡','MUS'),('MUS-021','古箏','MUS'),('PLG-001','C','PLG'),('PLG-002','Java','PLG'),('PLG-003','Python','PLG'),('PLG-004','C++','PLG'),('PLG-005','R','PLG'),('PLG-006','C#','PLG'),('PLG-007','PHP','PLG'),('PLG-008','Javascript','PLG'),('PLG-009','Ruby','PLG'),('PLG-010','Go','PLG'),('PLG-011','Matlab','PLG'),('PLG-012','Swift','PLG'),('PLG-013','VB','PLG'),('PLG-014','Perl','PLG'),('PLG-015','Html','PLG'),('PLG-016','Android','PLG'),('SKL-001','魔術','SKL'),('SKL-002','扯鈴','SKL'),('SPT-001','跳水','SPT'),('SPT-002','游泳','SPT'),('SPT-003','水上芭蕾','SPT'),('SPT-004','水球','SPT'),('SPT-005','輕艇','SPT'),('SPT-006','自行車','SPT'),('SPT-007','體操','SPT'),('SPT-008','排球','SPT'),('SPT-009','場地障礙賽','SPT'),('SPT-010','射箭','SPT'),('SPT-011','田徑','SPT'),('SPT-012','羽毛球','SPT'),('SPT-013','籃球','SPT'),('SPT-014','拳擊','SPT');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `videos` (
  `skill_id` int(11) NOT NULL,
  `video` varchar(2083) NOT NULL,
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `videos_ibfk_1` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (1,'@URL'),(1,'@URL_1'),(1,'@URL_2');
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-06 14:10:30
