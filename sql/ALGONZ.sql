CREATE DATABASE  IF NOT EXISTS `algonz` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `algonz`;
-- MySQL dump 10.13  Distrib 5.6.12, for osx10.7 (x86_64)
--
-- Host: 127.0.0.1    Database: algonz
-- ------------------------------------------------------
-- Server version	5.6.12

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
-- Table structure for table `actuacion`
--

DROP TABLE IF EXISTS `actuacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actuacion` (
  `CN_ACTUACION` int(8) NOT NULL AUTO_INCREMENT,
  `CN_SINIESTRO` int(8) NOT NULL,
  `FE_INICIO` date DEFAULT NULL,
  `FE_VENCIMIENTO` date DEFAULT NULL,
  `FE_CIERRE` date DEFAULT NULL,
  `CN_ESTADO` int(4) NOT NULL,
  `TE_NUMERO_EXP` varchar(50) DEFAULT NULL,
  `TE_DESCRIPCION` varchar(50) NOT NULL,
  `TE_OBSERVACIONES` text,
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_ACTUACION`),
  KEY `CN_ESTADO` (`CN_ESTADO`),
  KEY `CN_SINIESTRO` (`CN_SINIESTRO`),
  CONSTRAINT `FK_ACTUACION_ESTADO` FOREIGN KEY (`CN_ESTADO`) REFERENCES `estado` (`CN_ESTADO`),
  CONSTRAINT `FK_ACTUACION_SINIESTRO` FOREIGN KEY (`CN_SINIESTRO`) REFERENCES `siniestro` (`CN_SINIESTRO`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aviso_empresa`
--

DROP TABLE IF EXISTS `aviso_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aviso_empresa` (
  `CN_AVISO_EMPRESA` int(8) NOT NULL AUTO_INCREMENT COMMENT '	',
  `CN_EMPRESA_COMUNIDAD` int(8) NOT NULL,
  `TE_DESCRIPCION` varchar(100) DEFAULT NULL,
  `FE_INICIO` date DEFAULT NULL,
  `FE_VENCIMIENTO` date DEFAULT NULL,
  `FE_CIERRE` date DEFAULT NULL,
  `CN_ESTADO` int(4) NOT NULL,
  `TE_OBSERVACIONES` text,
  `NU_DIAS_AVISO` int(3) DEFAULT NULL,
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_AVISO_EMPRESA`),
  KEY `CN_EMPRESA_COMUNIDAD` (`CN_EMPRESA_COMUNIDAD`),
  KEY `CN_ESTADO` (`CN_ESTADO`),
  CONSTRAINT `FK_AVISO_EMPRESA_EMPRESA_COMUNIDAD` FOREIGN KEY (`CN_EMPRESA_COMUNIDAD`) REFERENCES `empresa_comunidad` (`CN_EMPRESA_COMUNIDAD`),
  CONSTRAINT `FK_AVISO_EMPRESA_ESTADO` FOREIGN KEY (`CN_ESTADO`) REFERENCES `estado` (`CN_ESTADO`)
) ENGINE=InnoDB AUTO_INCREMENT=333 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comunidad`
--

DROP TABLE IF EXISTS `comunidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comunidad` (
  `CN_COMUNIDAD` int(8) NOT NULL AUTO_INCREMENT,
  `CA_CIF` varchar(10) NOT NULL,
  `TE_NOMBRE` varchar(100) NOT NULL,
  `TE_CP` varchar(50) DEFAULT NULL,
  `TE_OBSERVACIONES` text,
  `IdUsuario` int(10) DEFAULT NULL COMMENT 'Identificador del usuario responsable de la comunidad',
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_COMUNIDAD`),
  KEY `ID_USUARIO` (`IdUsuario`),
  CONSTRAINT `FK_COMUNIDAD_USUARIO` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `documento`
--

DROP TABLE IF EXISTS `documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documento` (
  `CN_DOCUMENTO` int(10) NOT NULL AUTO_INCREMENT,
  `TE_NOMBRE` varchar(100) NOT NULL,
  `TE_PATH` varchar(500) NOT NULL,
  `FE_GUARDADO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CN_COMUNIDAD` int(8) DEFAULT NULL,
  `CN_ACTUACION` int(8) DEFAULT NULL,
  `CN_SINIESTRO` int(8) DEFAULT NULL,
  `CN_PREDIO` int(8) DEFAULT NULL,
  `CN_EMPRESA_COMUNIDAD` int(8) DEFAULT NULL,
  `CN_EMPRESA` int(8) DEFAULT NULL,
  `CN_AVISO_EMPRESA` int(8) DEFAULT NULL,
  `TE_FILE_TYPE` varchar(50) DEFAULT NULL,
  `NU_FILE_SIZE` int(8) DEFAULT NULL,
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_DOCUMENTO`),
  KEY `fk_documento_comunidad1_idx` (`CN_COMUNIDAD`),
  KEY `fk_documento_actuacion1_idx` (`CN_ACTUACION`),
  KEY `fk_documento_siniestro1_idx` (`CN_SINIESTRO`),
  KEY `fk_documento_predio1_idx` (`CN_PREDIO`),
  KEY `fk_documento_empresa_comunidad1_idx` (`CN_EMPRESA_COMUNIDAD`),
  KEY `fk_documento_empresa1_idx` (`CN_EMPRESA`),
  KEY `fk_documento_aviso_empresa1_idx` (`CN_AVISO_EMPRESA`),
  CONSTRAINT `fk_documento_actuacion1` FOREIGN KEY (`CN_ACTUACION`) REFERENCES `actuacion` (`CN_ACTUACION`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_documento_aviso_empresa1` FOREIGN KEY (`CN_AVISO_EMPRESA`) REFERENCES `aviso_empresa` (`CN_AVISO_EMPRESA`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_documento_comunidad1` FOREIGN KEY (`CN_COMUNIDAD`) REFERENCES `comunidad` (`CN_COMUNIDAD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_documento_empresa1` FOREIGN KEY (`CN_EMPRESA`) REFERENCES `empresa` (`CN_EMPRESA`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_documento_empresa_comunidad1` FOREIGN KEY (`CN_EMPRESA_COMUNIDAD`) REFERENCES `empresa_comunidad` (`CN_EMPRESA_COMUNIDAD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_documento_predio1` FOREIGN KEY (`CN_PREDIO`) REFERENCES `predio` (`CN_PREDIO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_documento_siniestro1` FOREIGN KEY (`CN_SINIESTRO`) REFERENCES `siniestro` (`CN_SINIESTRO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=895 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `CN_EMPRESA` int(8) NOT NULL AUTO_INCREMENT,
  `CN_TIPO_EMPRESA` int(4) NOT NULL,
  `TE_NOMBRE` varchar(100) DEFAULT NULL,
  `CA_CIF` varchar(10) DEFAULT NULL,
  `TE_DIRECCION` text,
  `TE_TLF_FIJO` varchar(50) DEFAULT NULL,
  `TE_TLF_MOVIL1` varchar(50) DEFAULT NULL,
  `TE_TLF_MOVIL2` varchar(50) DEFAULT NULL,
  `TE_EMAIL` varchar(50) DEFAULT NULL,
  `TE_OBSERVACIONES` text,
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_EMPRESA`),
  KEY `fk_empresa_tipo_empresa1_idx` (`CN_TIPO_EMPRESA`),
  CONSTRAINT `fk_empresa_tipo_empresa1` FOREIGN KEY (`CN_TIPO_EMPRESA`) REFERENCES `tipo_empresa` (`CN_TIPO_EMPRESA`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=273 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `empresa_comunidad`
--

DROP TABLE IF EXISTS `empresa_comunidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa_comunidad` (
  `CN_EMPRESA_COMUNIDAD` int(8) NOT NULL AUTO_INCREMENT,
  `CN_COMUNIDAD` int(8) NOT NULL,
  `CN_EMPRESA` int(8) NOT NULL,
  `FE_INICIO` date DEFAULT NULL,
  `FE_FIN` date DEFAULT NULL,
  `TE_OBSERVACIONES` text,
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_EMPRESA_COMUNIDAD`),
  KEY `CN_COMUNIDAD` (`CN_COMUNIDAD`),
  KEY `CN_EMPRESA` (`CN_EMPRESA`),
  CONSTRAINT `FK_EMPRESA_COMUNIDAD_COMUNIDAD` FOREIGN KEY (`CN_COMUNIDAD`) REFERENCES `comunidad` (`CN_COMUNIDAD`),
  CONSTRAINT `FK_EMPRESA_COMUNIDAD_EMPRESA` FOREIGN KEY (`CN_EMPRESA`) REFERENCES `empresa` (`CN_EMPRESA`)
) ENGINE=InnoDB AUTO_INCREMENT=633 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `CN_ESTADO` int(4) NOT NULL AUTO_INCREMENT,
  `TE_ESTADO` varchar(50) NOT NULL,
  PRIMARY KEY (`CN_ESTADO`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'Abierta'),(2,'Cerrada'),(3,'Pendiente de tercero'),(4,'Aplazado para junta'),(5,'Mantenimiento');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `planta`
--

DROP TABLE IF EXISTS `planta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planta` (
  `CN_PLANTA` int(4) NOT NULL AUTO_INCREMENT,
  `TE_PLANTA` varchar(50) NOT NULL,
  PRIMARY KEY (`CN_PLANTA`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `planta`
--

LOCK TABLES `planta` WRITE;
/*!40000 ALTER TABLE `planta` DISABLE KEYS */;
INSERT INTO `planta` VALUES (1,'LOCAL'),(2,'BJ'),(3,'ENTL'),(4,'1º'),(5,'2º'),(6,'3º'),(7,'4º'),(8,'5º'),(9,'6º'),(10,'7º'),(11,'8º'),(12,'9º'),(13,'10º'),(14,'11º'),(15,'12º'),(16,'13º'),(17,'14º'),(18,'15º'),(19,'BC'),(20,'G1'),(21,'G2'),(22,'G3');
/*!40000 ALTER TABLE `planta` ENABLE KEYS */;
UNLOCK TABLES;

	
--
-- Table structure for table `portal`
--

DROP TABLE IF EXISTS `portal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `portal` (
  `CN_PORTAL` int(8) NOT NULL AUTO_INCREMENT,
  `CN_COMUNIDAD` int(8) NOT NULL,
  `TE_CALLE` varchar(100) DEFAULT NULL,
  `TE_NOMBRE` varchar(100) NOT NULL,
  `TE_OBSERVACIONES` text,
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_PORTAL`),
  KEY `CN_COMUNIDAD` (`CN_COMUNIDAD`),
  CONSTRAINT `FK_PORTAL_COMUNIDAD` FOREIGN KEY (`CN_COMUNIDAD`) REFERENCES `comunidad` (`CN_COMUNIDAD`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `predio`
--

DROP TABLE IF EXISTS `predio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `predio` (
  `CN_PREDIO` int(8) NOT NULL AUTO_INCREMENT,
  `CN_PORTAL` int(8) NOT NULL,
  `CN_TIPO_PREDIO` int(4) NOT NULL,
  `CN_PLANTA` int(4) NOT NULL,
  `TE_PREDIO` varchar(50) DEFAULT NULL,
  `CN_PROPIETARIO` int(8) NOT NULL,
  `CN_CONYUGE` int(8) DEFAULT NULL,
  `CN_INQUILINO` int(8) DEFAULT NULL,
  `TE_PISO` varchar(50) DEFAULT NULL,
  `TE_TRASTERO` varchar(50) DEFAULT NULL,
  `TE_PLAZA` varchar(50) DEFAULT NULL,
  `TE_OBSERVACIONES` text,
  `CN_TIPO_REPRESENTANTE` int(4) DEFAULT NULL,
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_PREDIO`),
  KEY `CN_INQUILINO` (`CN_INQUILINO`),
  KEY `CN_PORTAL` (`CN_PORTAL`),
  KEY `CN_PROPIETARIO` (`CN_PROPIETARIO`),
  KEY `CN_CONYUGE` (`CN_CONYUGE`),
  KEY `FK_PREDIO_TIPO_REPRESENTANTE1_idx` (`CN_TIPO_REPRESENTANTE`),
  KEY `FK_PREDIO_TIPO_PREDIO1_idx` (`CN_TIPO_PREDIO`),
  KEY `FK_PREDIO_PLANTA_idx` (`CN_PLANTA`),
  CONSTRAINT `FK_PREDIO_CONYUGE` FOREIGN KEY (`CN_CONYUGE`) REFERENCES `tercero` (`CN_TERCERO`),
  CONSTRAINT `FK_PREDIO_INQUILINO` FOREIGN KEY (`CN_INQUILINO`) REFERENCES `tercero` (`CN_TERCERO`),
  CONSTRAINT `FK_PREDIO_PLANTA` FOREIGN KEY (`CN_PLANTA`) REFERENCES `planta` (`CN_PLANTA`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PREDIO_PORTAL` FOREIGN KEY (`CN_PORTAL`) REFERENCES `portal` (`CN_PORTAL`),
  CONSTRAINT `FK_PREDIO_PROPIETARIO` FOREIGN KEY (`CN_PROPIETARIO`) REFERENCES `tercero` (`CN_TERCERO`),
  CONSTRAINT `FK_PREDIO_TIPO_PREDIO` FOREIGN KEY (`CN_TIPO_PREDIO`) REFERENCES `tipo_predio` (`CN_TIPO_PREDIO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PREDIO_TIPO_REPRESENTANTE1` FOREIGN KEY (`CN_TIPO_REPRESENTANTE`) REFERENCES `tipo_representante` (`CN_TIPO_REPRESENTANTE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2137 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `IdRol` int(10) NOT NULL,
  `Authority` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `DescRol` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROLE_ADMIN','admin'),(2,'ROLE_USER','user');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `siniestro`
--

DROP TABLE IF EXISTS `siniestro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `siniestro` (
  `CN_SINIESTRO` int(8) NOT NULL AUTO_INCREMENT,
  `CN_PORTAL` int(8) NOT NULL,
  `TE_NOMBRE` varchar(100) DEFAULT NULL,
  `TE_OBSERVACIONES` text,
  `CN_EMPRESA_COMUNIDAD` int(8) NOT NULL,
  `CreatedBy` varchar(30) DEFAULT NULL,
  `CreatedDtTm` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(30) DEFAULT NULL,
  `ModifiedDtTm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CN_SINIESTRO`),
  KEY `CN_PORTAL` (`CN_PORTAL`),
  KEY `fk_siniestro_empresa_comunidad1_idx` (`CN_EMPRESA_COMUNIDAD`),
  CONSTRAINT `fk_siniestro_empresa_comunidad1` FOREIGN KEY (`CN_EMPRESA_COMUNIDAD`) REFERENCES `empresa_comunidad` (`CN_EMPRESA_COMUNIDAD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SINIESTRO_PORTAL` FOREIGN KEY (`CN_PORTAL`) REFERENCES `portal` (`CN_PORTAL`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tercero`
--

DROP TABLE IF EXISTS `tercero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tercero` (
  `CN_TERCERO` int(8) NOT NULL AUTO_INCREMENT,
  `TE_NOMBRE` varchar(100) NOT NULL,
  `TE_APELLIDO1` varchar(50) DEFAULT NULL,
  `TE_APELLIDO2` varchar(50) DEFAULT NULL,
  `CA_NIF` varchar(10) DEFAULT NULL,
  `TE_TLF_FIJO` varchar(50) DEFAULT NULL,
  `TE_TLF_MOVIL1` varchar(50) DEFAULT NULL,
  `TE_TLF_MOVIL2` varchar(50) DEFAULT NULL,
  `CN_BANCO` varchar(25) DEFAULT NULL,
  `NU_CUENTA_CORRIENTE` varchar(25) DEFAULT NULL,
  `TE_DIRECCION` text,
  `TE_DIRECCION_SECUNDARIA` text,
  `TE_EMAIL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CN_TERCERO`)
) ENGINE=InnoDB AUTO_INCREMENT=6444 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_empresa`
--

DROP TABLE IF EXISTS `tipo_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_empresa` (
  `CN_TIPO_EMPRESA` int(4) NOT NULL AUTO_INCREMENT,
  `TE_TIPO_EMPRESA` varchar(50) NOT NULL,
  PRIMARY KEY (`CN_TIPO_EMPRESA`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



LOCK TABLES `tipo_empresa` WRITE;
/*!40000 ALTER TABLE `tipo_empresa` DISABLE KEYS */;
INSERT INTO `tipo_empresa` VALUES (1,'Abogados'),(2,'Administradores de fincas'),(3,'Antenistas'),(4,'Arquitectos'),(5,'Ascensores'),(6,'Carpinterías metálicas'),(7,'Cerrajeros'),(8,'Constructoras'),(9,'Contratistas'),(10,'Desatascos'),(11,'Desinfección'),(12,'E.M.A'),(13,'Electricistas'),(14,'Entidades financieras'),(15,'Fontaneros'),(16,'Grupos de presión'),(17,'Jardinería'),(18,'Limpiezas'),(19,'Mantenimiento calderas'),(20,'Mantenimiento de piscinas'),(21,'O.C.A.S'),(22,'Organismos Gubernamentales'),(23,'Otras'),(24,'Pintores'),(25,'Portones'),(26,'Protección contra incendios'),(27,'Seguros'),(28,'Suministro comercializadora'),(29,'Telefonía'),(30,'Presupuestos');
INSERT INTO `algonz`.`tipo_empresa` (`CN_TIPO_EMPRESA`, `TE_TIPO_EMPRESA`) VALUES ('31', 'Carpinteros');
INSERT INTO `algonz`.`tipo_empresa` (`CN_TIPO_EMPRESA`, `TE_TIPO_EMPRESA`) VALUES ('32', 'Clientes asesoría');
/*!40000 ALTER TABLE `tipo_empresa` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `tipo_predio`
--

DROP TABLE IF EXISTS `tipo_predio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_predio` (
  `CN_TIPO_PREDIO` int(4) NOT NULL AUTO_INCREMENT,
  `TE_TIPO_PREDIO` varchar(50) NOT NULL,
  PRIMARY KEY (`CN_TIPO_PREDIO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `tipo_predio`
--

LOCK TABLES `tipo_predio` WRITE;
/*!40000 ALTER TABLE `tipo_predio` DISABLE KEYS */;
INSERT INTO `tipo_predio` VALUES (1,'Piso'),(2,'Garaje');
/*!40000 ALTER TABLE `tipo_predio` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `tipo_representante`
--

DROP TABLE IF EXISTS `tipo_representante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_representante` (
  `CN_TIPO_REPRESENTANTE` int(4) NOT NULL AUTO_INCREMENT,
  `TE_TIPO_REPRESENTANTE` varchar(50) NOT NULL,
  PRIMARY KEY (`CN_TIPO_REPRESENTANTE`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `tipo_representante`
--

LOCK TABLES `tipo_representante` WRITE;
/*!40000 ALTER TABLE `tipo_representante` DISABLE KEYS */;
INSERT INTO `tipo_representante` VALUES (1,'Presidente'),(2,'Vicepresidente'),(3,'Vocal 1'),(4,'Vocal 2'),(5,'Vocal 3'),(6,'Vocal 4'),(7,'Vocal 5');
/*!40000 ALTER TABLE `tipo_representante` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `IdUsuario` int(10) NOT NULL AUTO_INCREMENT,
  `IdSistema` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `PwdSistema` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `teNombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `teApellido1` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `teApellido2` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `teEmail` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (6,'qqq','qqq','yhhh','','',NULL,1),(7,'hhhhhh','','','','',NULL,0),(8,'uuuuuuuuu','','sfsdfasdf','asfdsfdsaf','',NULL,0),(9991,'johnny','711383a59fda05336fd2ccf70c8059d1523eb41a','Jonathan','Merayo','ooo',NULL,1),(9992,'angeles','711383a59fda05336fd2ccf70c8059d1523eb41a','','','',NULL,1),(9993,'administrativo','711383a59fda05336fd2ccf70c8059d1523eb41a',NULL,NULL,NULL,NULL,1),(9994,'lulu','711383a59fda05336fd2ccf70c8059d1523eb41a',NULL,NULL,NULL,NULL,1),(9995,'jose ramon','711383a59fda05336fd2ccf70c8059d1523eb41a',NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_rol` (
  `IdUsuario` int(10) NOT NULL,
  `IdRol` int(10) NOT NULL,
  PRIMARY KEY (`IdUsuario`,`IdRol`),
  KEY `FK_usuario_rol_rol` (`IdRol`),
  CONSTRAINT `FK_usuario_rol_rol` FOREIGN KEY (`IdRol`) REFERENCES `rol` (`IdRol`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_usuario_rol_usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (9991,1),(9992,2),(9993,2),(9994,2),(9995,2);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-06 19:07:20
