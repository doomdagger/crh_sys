-- MySQL dump 10.13  Distrib 5.5.17, for osx10.6 (i386)
--
-- Host: localhost    Database: train_sys
-- ------------------------------------------------------
-- Server version	5.5.17
m_train
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
-- Table structure for table `m_train`
--

use train_sys;

DROP TABLE IF EXISTS `m_train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_train` (
  `ID` int(11) NOT NULL,
  `FCRHType` varchar(30) NOT NULL,
  `FCRHModelType` varchar(20) NOT NULL,
  `Cart1` int(11) NOT NULL DEFAULT '-1',
  `Cart2` int(11) NOT NULL DEFAULT '-1',
  `Cart3` int(11) NOT NULL DEFAULT '-1',
  `Cart4` int(11) NOT NULL DEFAULT '-1',
  `Cart5` int(11) NOT NULL DEFAULT '-1',
  `Cart6` int(11) NOT NULL DEFAULT '-1',
  `Cart7` int(11) NOT NULL DEFAULT '-1',
  `Cart8` int(11) NOT NULL DEFAULT '-1',
  `Cart9` int(11) NOT NULL DEFAULT '-1',
  `Cart10` int(11) NOT NULL DEFAULT '-1',
  `Cart11` int(11) NOT NULL DEFAULT '-1',
  `Cart12` int(11) NOT NULL DEFAULT '-1',
  `Cart13` int(11) NOT NULL DEFAULT '-1',
  `Cart14` int(11) NOT NULL DEFAULT '-1',
  `Cart15` int(11) NOT NULL DEFAULT '-1',
  `Cart16` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_train`
--

LOCK TABLES `m_train` WRITE;
/*!40000 ALTER TABLE `m_train` DISABLE KEYS */;
INSERT INTO `m_train` VALUES (1,'CRH1A','5M3T',1,1,0,1,1,0,1,0,-1,-1,-1,-1,-1,-1,-1,-1),(2,'CRH2A','4M4T',1,1,1,1,0,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1),(3,'CRH2B','8M8T',1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0),(4,'CRH2C','6M2T',1,1,1,1,1,1,0,0,-1,-1,-1,-1,-1,1,-1,-1),(5,'CRH2E','12M4T',1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0),(6,'CRH3C','4M4T',1,1,1,1,0,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1),(7,'CRH5A','5M3T',1,1,1,1,1,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1),(8,'CRH380A','6M2T',1,1,1,1,1,1,0,0,-1,-1,-1,-1,-1,-1,-1,-1),(9,'CRH380AL','14M2T',1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0),(10,'CRH380B','4M4T',1,1,1,1,0,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1),(11,'CRH380BL','8M8T',1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0);
/*!40000 ALTER TABLE `m_train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_crh`
--

DROP TABLE IF EXISTS `t_crh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_crh` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FCRHNo` varchar(100) NOT NULL,
  `FCRHModelType` varchar(50) NOT NULL,
  `FCRHType` varchar(20) NOT NULL,
  `FFactory` varchar(200) NOT NULL,
  `FProduceDate` datetime DEFAULT NULL,
  `FRouteline` varchar(100) DEFAULT NULL,
  `FRemark` varchar(200) DEFAULT NULL,
  `FCreatetime` datetime DEFAULT NULL,
  `FEdittime` datetime DEFAULT NULL,
  `FCreator` bigint(20) DEFAULT NULL,
  `FEditor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_crh`
--

LOCK TABLES `t_crh` WRITE;
/*!40000 ALTER TABLE `t_crh` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_crh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_engine`
--

DROP TABLE IF EXISTS `t_engine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_engine` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FEngineID` varchar(100) NOT NULL,
  `id_t_crh` bigint(20) NOT NULL,
  `FSetupType` varchar(200) DEFAULT NULL,
  `FSuitTrain` varchar(200) DEFAULT NULL,
  `FPower` int(11) DEFAULT NULL,
  `Fvoltage` int(11) DEFAULT NULL,
  `FNumber` int(11) DEFAULT NULL,
  `FLineType` varchar(50) DEFAULT NULL,
  `FJYRank` varchar(50) DEFAULT NULL,
  `FCoolType` varchar(20) DEFAULT NULL,
  `FWeight` int(11) DEFAULT NULL,
  `FMaxrevolution` int(11) DEFAULT NULL,
  `FWorkType` varchar(100) DEFAULT NULL,
  `FTFQuality` varchar(200) DEFAULT NULL,
  `FDiaTemper` varchar(500) DEFAULT NULL,
  `FDiaSpeed` varchar(500) DEFAULT NULL,
  `FProduceFac` varchar(200) DEFAULT NULL,
  `FProduceDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `tran_fk` (`id_t_crh`),
  CONSTRAINT `tran_fk` FOREIGN KEY (`id_t_crh`) REFERENCES `t_crh` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_engine`
--

LOCK TABLES `t_engine` WRITE;
/*!40000 ALTER TABLE `t_engine` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_engine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_realtimedata`
--

DROP TABLE IF EXISTS `t_realtimedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_realtimedata` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `idt_engine` bigint(20) NOT NULL,
  `FCRHNo` varchar(50) NOT NULL,
  `Fyb_dianya` float NOT NULL,
  `Fyb_dianliu` float NOT NULL,
  `Fkz_dianya` float DEFAULT NULL,
  `Fzj_dianya` float DEFAULT NULL,
  `Fdj_dianliu` float DEFAULT NULL,
  `Fdj_pinlv` float DEFAULT NULL,
  `Fzdxinhao` float DEFAULT NULL,
  `Fdzdianliu` float DEFAULT NULL,
  `Fqy_kongzhili` float DEFAULT NULL,
  `FSpeed` float DEFAULT NULL,
  `Fjiasudu` float DEFAULT NULL,
  `Ftemperature` float DEFAULT NULL,
  `FDatatime` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `engine_fk` (`idt_engine`),
  CONSTRAINT `engine_fk` FOREIGN KEY (`idt_engine`) REFERENCES `t_engine` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_realtimedata`
--

LOCK TABLES `t_realtimedata` WRITE;
/*!40000 ALTER TABLE `t_realtimedata` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_realtimedata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-04 10:28:55
